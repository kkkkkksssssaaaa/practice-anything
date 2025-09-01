package webserver.messages

object Messages {
    fun okResponseHeaderTemplate(lengthOfBodyContent: Int) = """
        HTTP/1.1 200 OK\r\n
        Content-Length: $lengthOfBodyContent\r\n
        Content-Type: text/html; charset=UTF-8\r\n
        Connection: close\r\n
        \r\n
    """.trimIndent()

    fun notFoundResponseHeaderTemplate() = """
        HTTP/1.1 404 Not Found\r\n
        Content-Length: 13\r\n
        Content-Type: text/html; charset=UTF-8\r\n
        Connection: close\r\n
        \r\n
    """.trimIndent()

    fun notFoundBody() = "404 Not Found"

    fun newClientConnected(
        inetAddress: String,
        port: Int
    ) = """
        New Client Connect! Connected IP: $inetAddress, port: $port
    """.trimIndent()
}