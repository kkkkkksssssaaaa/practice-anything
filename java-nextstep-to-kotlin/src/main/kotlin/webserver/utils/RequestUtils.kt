package webserver.utils

import common.factory.models.annotations.Component
import common.factory.models.servlet.models.HttpStatus
import mu.KotlinLogging
import webserver.messages.Messages.header
import webserver.messages.Messages.notFoundBody
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
        status: HttpStatus,
        dos: DataOutputStream,
        bodyContent: ByteArray?,
    ) {
        val header: Pair<String, ByteArray?> = when (status) {
            HttpStatus.NOT_FOUND -> {
                val body = notFoundBody()

                header(
                    status = status,
                    lengthOfBodyContent = body.length,
                    contentType = "application/json"
                ) to body.toByteArray()
            }
            else -> {
                header(
                    status = status,
                    lengthOfBodyContent = bodyContent?.size ?: 0,
                    contentType = "application/json",
                ) to bodyContent
            }
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