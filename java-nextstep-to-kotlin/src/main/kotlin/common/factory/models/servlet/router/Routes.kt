package common.factory.models.servlet.router

import common.factory.models.servlet.models.HandlerFunction
import java.util.concurrent.ConcurrentHashMap

class Routes(
    private val routes: MutableMap<String, HandlerFunction> =
        ConcurrentHashMap<String, HandlerFunction>(128)
) {
    operator fun get(name: String): HandlerFunction? {
        val findResult = this.routes[name]

        if (findResult != null) {
            return findResult
        }

        val (method, path) = name.split(" ", limit = 2)

        for ((routeKey, handler) in this.routes) {
            val (routeMethod, routePattern) = routeKey.split(" ", limit = 2)

            if (routeMethod != method) continue

            val regexPattern = routePattern
                .replace(Regex("\\{[^/]+\\}"), "([^/]+)")
                .let { "^$it$" }

            if (Regex(regexPattern).matches(path)) {
                return handler
            }
        }

        return null
    }

    fun putAll(routes: Map<String, HandlerFunction>) {
        this.routes.putAll(routes)
    }
}