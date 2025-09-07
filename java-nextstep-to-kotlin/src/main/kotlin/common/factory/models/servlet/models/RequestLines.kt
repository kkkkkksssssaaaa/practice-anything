package common.factory.models.servlet.models

import java.net.URLDecoder

data class RequestLines(
    private val values: List<String>
) {
    fun isStaticResourceRequest(): Boolean {
        return !isDynamicResourceRequest()
    }

    fun signature(): String {
        return "${this.method()} ${this.resource()}"
    }

    fun firstLine(): String {
        if (this.values.isEmpty()) {
            throw IllegalArgumentException("Empty lines")
        }

        return values.first()
    }

    fun method(): HttpMethod {
        val splitValue = firstLine().split(" ")[0]

        return HttpMethod.nameOf(splitValue)
    }

    fun resource(): String {
        return firstLine().split(" ")[1]
            .split("?")[0]
    }

    fun queryParameters(): Map<String, Any>? {
        val line = firstLine().split(" ")[1]
            .split("?")

        if (line.size == 1) {
            return null
        }

        return line[1]
            .split("&")
            .associate { pair ->
                val parts = pair.split("=")

                if (parts.size == 2) {
                    val key = URLDecoder.decode(parts[0], "UTF-8")
                    val value = URLDecoder.decode(parts[1], "UTF-8")
                    key to value
                } else {
                    throw IllegalArgumentException("Invalid query string")
                }
            }
    }

    private fun isDynamicResourceRequest(): Boolean {
        val findHeader = values.firstOrNull {
            it.startsWith("Content-Type")
        }

        if (findHeader == null) {
            return false
        }

        return findHeader.contains("application/json")
    }
}