package common.factory.models.servlet.models

data class RequestLines(
    private val values: List<String>
) {
    fun isStaticResourceRequest(): Boolean {
        return !isDynamicResourceRequest()
    }

    fun firstLine(): String {
        if (this.values.isEmpty()) {
            throw IllegalArgumentException("Empty lines")
        }

        return values.first()
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