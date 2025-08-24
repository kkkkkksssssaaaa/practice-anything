package common.factory.models.servlet.router

import common.factory.models.servlet.models.RequestLines
import mu.KotlinLogging
import webserver.utils.RequestHeaderExtractor.extractResourceName
import webserver.utils.RequestHeaderExtractor.writeHeader
import webserver.utils.RequestHeaderExtractor.writeResponseBody
import java.io.DataOutputStream
import java.io.File

internal object StaticResourceRouter {
    private val log = KotlinLogging.logger {}
    private val resourcePath = "java-nextstep-to-kotlin/webapp"

    fun doRoute(
        request: RequestLines,
        dos: DataOutputStream,
    ) {
        val resource = extractResourceName(request.firstLine())

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
