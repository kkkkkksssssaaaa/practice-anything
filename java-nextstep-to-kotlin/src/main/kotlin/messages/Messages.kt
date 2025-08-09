package messages

object Messages {
    fun okResponseTemplate(lengthOfBodyContent: Int) = """
        HTTP/1.1 200 OK
        Content-Length: $lengthOfBodyContent
        Content-Type: text/html;charset=utf-8;
            
        """.trimIndent()

    fun notFoundResponseTemplate() = """
        HTTP/1.1 404 Not Found
        Content-Length: 13
        Content-Type: text/html;charset=utf-8;
    
    """.trimIndent()

    fun notFoundBody() = "404 Not Found"

    fun newClientConnected(
        inetAddress: String,
        port: Int
    ) = """
        New Client Connect! Connected IP: ${inetAddress}, port: ${port}
    """.trimIndent()
}