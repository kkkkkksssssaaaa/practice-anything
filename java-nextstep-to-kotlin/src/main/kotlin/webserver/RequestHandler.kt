package webserver

import messages.Messages.newClientConnected
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

            val body = when {
                readLines.isEmpty() -> "Hello World".toByteArray()
                readLines[0].contains("index.html") -> {
                    val findFile = File("java-nextstep-to-kotlin/webapp/index.html")

                    if (!findFile.exists()) {
                        throw IllegalArgumentException("File does not exist: ${findFile.absolutePath}")
                    }

                    findFile.readBytes()
                }
                else -> "Hello World".toByteArray()
            }

            writeOkHeader(dos, body.size)
            writeResponseBody(dos, body)
        }
    }

    private fun writeOkHeader(
        dos: DataOutputStream,
        lengthOfBodyContent: Int
    ) {
        try {
            dos.writeBytes(okResponseTemplate(lengthOfBodyContent))
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