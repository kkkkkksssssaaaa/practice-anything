package webserver.messages

object Messages {
    fun okResponseHeaderTemplate(
        lengthOfBodyContent: Int,
        contentType: String?
    ): String = "HTTP/1.1 200 OK\r\n" +
            "Content-Length: $lengthOfBodyContent\r\n" +
            "Content-Type: ${contentType ?: "text/plain"}; charset=UTF-8\r\n" +
            "Connection: close\r\n" +
            "\r\n"

    fun notFoundResponseHeaderTemplate(
        contentType: String?
    ): String = "HTTP/1.1 404 Not Found\r\n" +
            "Content-Length: 13\r\n" +
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