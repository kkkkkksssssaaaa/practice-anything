package utils

import messages.Messages.notFoundResponseTemplate
import messages.Messages.okResponseTemplate
import mu.KotlinLogging
import java.io.DataOutputStream
import java.io.IOException

object RequestHeaderExtractor {
    private val regex = Regex("^(GET|POST|PUT|DELETE|PATCH|OPTIONS|HEAD)")
    private val log = KotlinLogging.logger {}

    fun extractMethod(firstLine: String): String {
        val result = regex.matchEntire(firstLine)?.let { match ->
            match.groupValues[0]
        }

        if (result == null) {
            throw IllegalArgumentException("Invalid Request. Header=$firstLine")
        }

        return result
    }

    fun extractResourceName(firstLine: String): String {
        if (firstLine.isEmpty()) {
            throw IllegalArgumentException("Invalid Request. Header=$firstLine")
        }

        val result = firstLine.split(" ")

        if (result.size == 1) {
            throw IllegalArgumentException("Invalid Request. Header=$firstLine")
        }

        return result[1].removePrefix("/")
    }

    fun writeHeader(
        statusCode: Int,
        dos: DataOutputStream,
        bodyContent: ByteArray?,
    ) {
        val headerAndResponse: Pair<String, ByteArray?> = when (statusCode) {
            200 -> {
                assert(bodyContent != null)
                Pair(okResponseTemplate(bodyContent!!.size), bodyContent)
            }
            404 -> Pair(notFoundResponseTemplate(), "404 Not Found".toByteArray())
            else -> throw IllegalArgumentException("Unknown status code: $statusCode")
        }

        try {
            dos.writeBytes(headerAndResponse.first)
            headerAndResponse.second?.let { writeResponseBody(dos, it) }
        } catch (e: IOException) {
            log.error(e.message, e)
        }
    }

    fun writeResponseBody(
        dos: DataOutputStream,
        body: ByteArray,
    ) {
        try {
            dos.write(body, 0, body.size)
            dos.writeBytes("\r\n")
            dos.flush()
        } catch (e: IOException) {
            log.error(e.message, e)
        }
    }
}