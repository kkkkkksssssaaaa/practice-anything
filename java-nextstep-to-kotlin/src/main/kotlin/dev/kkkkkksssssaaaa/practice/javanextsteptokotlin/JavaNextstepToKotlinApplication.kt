package dev.kkkkkksssssaaaa.practice.javanextsteptokotlin

import dev.kkkkkksssssaaaa.practice.javanextsteptokotlin.handler.RequestHandler
import mu.KotlinLogging
import java.net.ServerSocket
import java.net.Socket

class WebServer {
    companion object {
        private val log = KotlinLogging.logger {}
        private const val DEFAULT_PORT = 8080

        fun main(args: Array<String>?) {
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
    }
}