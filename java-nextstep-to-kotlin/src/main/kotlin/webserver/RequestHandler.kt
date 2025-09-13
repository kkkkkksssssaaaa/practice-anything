package webserver

import common.factory.models.servlet.DispatcherServlet
import common.factory.models.servlet.models.HttpStatus
import common.factory.models.servlet.models.Request
import common.factory.models.servlet.models.RequestBody
import common.factory.models.servlet.models.RequestHeader
import mu.KotlinLogging
import webserver.messages.Messages.newClientConnected
import webserver.messages.Messages.notFoundBody
import webserver.utils.RequestHeaderExtractor.writeResponse
import java.io.BufferedReader
import java.io.DataOutputStream
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
                writeResponse(HttpStatus.NOT_FOUND, dos, body)
            }

            val requestHeader = RequestHeader(readLines)

            val contentLength = requestHeader.contentLength()

            val bodyBuilder = CharArray(contentLength)
            if (contentLength > 0) {
                reader.read(bodyBuilder, 0, contentLength)
            }

            val bodyString = String(bodyBuilder)

            val response: Pair<HttpStatus, ByteArray?> = DispatcherServlet.doRoute(
                Request(
                    header = requestHeader,
                    body = RequestBody(bodyString)
                )
            )

            writeResponse(
                status = response.first,
                bodyContent = response.second,
                dos = dos,
            )
        }
    }
}