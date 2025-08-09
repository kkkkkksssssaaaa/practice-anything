package webserver

import messages.Messages.newClientConnected
import messages.Messages.notFoundBody
import messages.Messages.notFoundResponseTemplate
import messages.Messages.okResponseTemplate
import mu.KotlinLogging
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.File
import java.io.IOException
import java.net.Socket

class RequestHandler(
    private val connection: Socket
): Thread() {
    companion object {
        private val log = KotlinLogging.logger {}
    }

    override fun run() {
        connection.use {
            log.debug(newClientConnected(it.inetAddress.toString(), it.port))

            val reader = BufferedReader(it.getInputStream().reader())
            val out = it.getOutputStream()
            val dos = DataOutputStream(out)

            val readLines = mutableListOf<String>()

            while (true) {
                val line = reader.readLine() ?: break

                if (line.isEmpty()) break

                readLines.add(line)
            }

            if (readLines.isEmpty()) {
                val body = notFoundBody().toByteArray()
                writeHeader(404, dos, body)
                writeResponseBody(dos, body)
            }

            val findFile = File("java-nextstep-to-kotlin/webapp/index.html")

            if (!findFile.exists()) {
                throw IllegalArgumentException("File does not exist: ${findFile.absolutePath}")
            }

            val body = findFile.readBytes()

            writeHeader(200, dos, body)
            writeResponseBody(dos, body)
        }
    }

    private fun writeHeader(
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