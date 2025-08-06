package webserver

import mu.KotlinLogging
import java.io.*
import java.net.Socket

class RequestHandler(
    private val connection: Socket
): Thread() {
    companion object {
        private val log = KotlinLogging.logger {}
    }

    override fun run() {
        log.debug{ "New Client Connect! Connected IP: ${connection.inetAddress}, port: ${connection.port}" }

        connection.getInputStream().use {
            val readLines = BufferedReader(it.reader()).readLines()

            if (readLines.isEmpty()) {
                val body: ByteArray = "Hello World".toByteArray()
                val out = connection.getOutputStream()
                val dos = DataOutputStream(out)
                writeOkHeader(dos, body.size)
                writeResponseBody(dos, body)

                return
            }

            if (readLines[0].contains("index.html")) {
                val findFile: File = File("java-nextstep-to-kotlin/webapp/index.html")

                if (!findFile.exists()) {
                    throw IllegalArgumentException("File does not exist: ${findFile.absolutePath}")
                }

                val body = findFile.readBytes()

                val out = connection.getOutputStream()
                val dos = DataOutputStream(out)
                writeOkHeader(dos, body.size)
                writeResponseBody(dos, body)

                return
            }

            val out = connection.getOutputStream()
            val dos = DataOutputStream(out)

            val body: ByteArray = "Hello World".toByteArray()

            writeOkHeader(dos, body.size)
            writeResponseBody(dos, body)
        }
    }

    private fun writeOkHeader(
        dos: DataOutputStream,
        lengthOfBodyContent: Int
    ) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK\r\n")
            dos.writeBytes("Content-Type: text/html;charset=utf8\r\n")
            dos.writeBytes("Content-Length: ${lengthOfBodyContent}\r\n")
            dos.writeBytes("\r\n")
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