package common.factory.models.servlet.router

import common.factory.models.servlet.models.HandlerFunction
import java.util.concurrent.ConcurrentHashMap

class Routes(
    private val routes: MutableMap<String, HandlerFunction> =
        ConcurrentHashMap<String, HandlerFunction>(128)
) {
    fun find(name: String): HandlerFunction? {
        val findResult = routes[name]

        if (findResult != null) {
            return findResult
        }

        TODO()
    }

    fun putAll(routes: Map<String, HandlerFunction>) {
        this.routes.putAll(routes)
    }
}