package webserver.messages

import common.factory.models.servlet.models.HttpStatus

object Messages {
    fun header(
        status: HttpStatus,
        lengthOfBodyContent: Int,
        contentType: String?
    ): String = "HTTP/1.1 ${status.code} ${status.description}\r\n" +
            "Content-Length: $lengthOfBodyContent\r\n" +
            "Content-Type: ${contentType ?: "text/plain"}; charset=UTF-8\r\n" +
            "Connection: close\r\n" +
            "\r\n"

    fun notFoundBody() = "404 Not Found"

    fun newClientConnected(
        inetAddress: String,
        port: Int
    ) = """
        New Client Connect! Connected IP: $inetAddress, port: $port
    """.trimIndent().replace("\n", "\r\n")
}