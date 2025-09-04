package webserver.utils

import common.factory.models.annotations.Component
import webserver.messages.Messages.notFoundResponseHeaderTemplate
import webserver.messages.Messages.okResponseHeaderTemplate
import mu.KotlinLogging
import java.io.DataOutputStream
import java.io.IOException

@Component
object RequestHeaderExtractor {
    private val log = KotlinLogging.logger {}

    init {
        log.info("Initialize RequestHeaderExtractor")
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

    fun writeResponse(
        statusCode: Int,
        dos: DataOutputStream,
        bodyContent: ByteArray?,
    ) {
        val header: Pair<String, ByteArray?> = when (statusCode) {
            200 -> {
                assert(bodyContent != null)

                okResponseHeaderTemplate(
                    lengthOfBodyContent = bodyContent!!.size,
                    contentType = "application/json" // TODO: inject dynamically
                ) to bodyContent
            }
            404 -> notFoundResponseHeaderTemplate("application/json") to "404 Not Found".toByteArray()
            else -> throw IllegalArgumentException("Unknown status code: $statusCode")
        }

        try {
            dos.writeBytes(header.first)
            header.second?.let { writeResponseBody(dos, it) }
        } catch (e: IOException) {
            log.error(e.message, e)
        }
    }

    private fun writeResponseBody(
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