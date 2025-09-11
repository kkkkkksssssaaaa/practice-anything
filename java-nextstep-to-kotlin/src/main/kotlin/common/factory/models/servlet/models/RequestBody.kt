package common.factory.models.servlet.models

class RequestBody(
    private val contentType: String,
    private val content: Map<String, Any>,
)