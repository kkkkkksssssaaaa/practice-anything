package dev.kkkkkksssssaaaa.practice.javanextsteptokotlin.handler

import mu.KotlinLogging
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket

class RequestHandler(
    val connection: Socket
): Thread() {
    companion object {
        private val log = KotlinLogging.logger {}
    }

    override fun run() {
        log.debug{ "New Client Connect! Connected IP: ${connection.inetAddress}, port: ${connection.port}" }

        connection.getInputStream().use {
            val out = connection.getOutputStream()

            // TODO: 사용자 요청 처리
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