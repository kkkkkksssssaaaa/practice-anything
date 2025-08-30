package webserver

import common.factory.BeanRegistry
import common.factory.models.servlet.DispatcherServlet
import common.factory.models.servlet.router.DynamicResourceRouter
import mu.KotlinLogging
import java.net.ServerSocket
import java.net.Socket

private val log = KotlinLogging.logger {}
private const val DEFAULT_PORT = 8080

fun main(args: Array<String>?) {
    BeanRegistry.init("domain")
    DispatcherServlet.lazyInit()

    var port = 0

    port = if (args == null || args.isEmpty()) {
        DEFAULT_PORT
    } else {
        args[0].toInt()
    }

    ServerSocket(port).use { listenSocket ->
        log.info{ "Web Application Server Started $port port." }

        var connection: Socket

        while ((listenSocket.accept().also { connection = it }) != null) {
            val requestHandler = RequestHandler(connection)
            requestHandler.start()
        }
    }
}