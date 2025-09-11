package common.factory.models.servlet.models

data class Request(
    val header: RequestHeader,
    val body: RequestBody?,
)