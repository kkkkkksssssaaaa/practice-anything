package common.factory.models.servlet.router

import common.factory.models.annotations.Component
import common.factory.models.servlet.models.HttpStatus
import common.factory.models.servlet.models.Request
import mu.KotlinLogging
import webserver.utils.RequestHeaderExtractor.extractResourceName
import java.io.File

@Component
internal object StaticResourceRouter: ResourceRouter {
    private val log = KotlinLogging.logger {}
    private val resourcePath = "java-nextstep-to-kotlin/webapp"

    override fun lazyInit() {
        log.info("Initialize StaticResourceRouter")
    }

    override fun doRoute(request: Request): Pair<HttpStatus, ByteArray?> {
        val resource = extractResourceName(request.header.firstLine())

        if (resource.startsWith(".")) {
            return HttpStatus.OK to null
        }

        val findFile = File("$resourcePath/${resource}")

        if (!findFile.exists()) {
            throw IllegalArgumentException("File does not exist: ${findFile.absolutePath}")
        }

        val body = findFile.readBytes()

        return HttpStatus.OK to body
    }
}
