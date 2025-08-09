package messages

object Messages {
    fun okResponseTemplate(lengthOfBodyContent: Int) = """
        HTTP/1.1 200 OK
        Content-Length: $lengthOfBodyContent
        Content-Type: text/html;charset=utf-8;
            
        """.trimIndent()

    fun newClientConnected(
        inetAddress: String,
        port: Int
    ) = """
        New Client Connect! Connected IP: ${inetAddress}, port: ${port}
    """.trimIndent()
}