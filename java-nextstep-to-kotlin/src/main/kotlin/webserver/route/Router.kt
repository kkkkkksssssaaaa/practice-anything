package webserver.route

import mu.KotlinLogging
import webserver.utils.RequestHeaderExtractor.extractResourceName
import webserver.utils.RequestHeaderExtractor.writeHeader
import webserver.utils.RequestHeaderExtractor.writeResponseBody
import java.io.DataOutputStream
import java.io.File

object Router {
    private val log = KotlinLogging.logger {}
    private val resourcePath = "java-nextstep-to-kotlin/webapp"
    private val path = setOf(
        "${HttpMethod.GET.name} /user/create"
    )

    fun doRoute(
        firstLine: String,
        dos: DataOutputStream,
    ) {
        val resource = extractResourceName(firstLine)

        if (resource.startsWith(".")) {
            return
        }

        val findFile = File("$resourcePath/${resource}")

        if (!findFile.exists()) {
            throw IllegalArgumentException("File does not exist: ${findFile.absolutePath}")
        }

        val body = findFile.readBytes()

        writeHeader(200, dos, body)
        writeResponseBody(dos, body)
    }
}

enum class HttpMethod {
    GET, POST, PUT, DELETE, PATCH, HEAD
}