package common.factory.models.servlet.models

import java.net.URLDecoder

class RequestHeader(
    private val lines: List<String>,
) {
    fun isStaticResourceRequest(): Boolean {
        return !isDynamicResourceRequest()
    }

    fun signature(): String {
        return "${this.method()} ${this.resource()}"
    }

    fun firstLine(): String {
        if (this.lines.isEmpty()) {
            throw IllegalArgumentException("Empty lines")
        }

        return lines.first()
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

    fun contentLength(): Int {
        val line = lines.firstOrNull {
            it.startsWith(
                "Content-Length",
                ignoreCase = true
            )
        } ?: return 0

        val index = line.indexOf(':')

        if (index == -1) {
            return 0
        }

        return line.substring(index + 1)
            .trim()
            .toIntOrNull() ?: 0
    }

    fun contentType(): String? {
        return this.lines.firstOrNull { it.contains("Content-Type") }
            ?.replace("Content-Type: ", "")
    }

    private fun isDynamicResourceRequest(): Boolean {
        val findHeader = contentType() ?: return false

        return findHeader == "application/json"
    }
}