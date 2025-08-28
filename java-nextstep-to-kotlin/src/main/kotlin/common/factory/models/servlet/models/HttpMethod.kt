package common.factory.models.servlet.models

enum class HttpMethod {
    GET, POST, PUT, DELETE, PATCH, HEAD;

    companion object {
        fun nameOf(value: String): HttpMethod {
            val find = entries.firstOrNull {
                it.name == value
            } ?: throw IllegalArgumentException()

            return find
        }
    }
}