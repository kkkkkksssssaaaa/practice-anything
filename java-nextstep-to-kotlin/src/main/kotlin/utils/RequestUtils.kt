package utils

object RequestHeaderExtractor {
    private val regex = Regex("^(GET|POST|PUT|DELETE|PATCH|OPTIONS|HEAD)")

    fun extractMethod(firstLine: String): String {
        val result = regex.matchEntire(firstLine)?.let { match ->
            match.groupValues[0]
        }

        if (result == null) {
            throw IllegalArgumentException("Invalid Request. Header=$firstLine")
        }

        return result
    }

    fun extractResourceName(firstLine: String): String {
        if (firstLine.isEmpty()) {
            throw IllegalArgumentException("Invalid Request. Header=$firstLine")
        }

        val result = firstLine.split(" ")

        if (result.size == 1) {
            throw IllegalArgumentException("Invalid Request. Header=$firstLine")
        }

        return result[1].removePrefix("/")
    }
}