package dev.kkkkkksssssaaaa.practice.templateserver.domain.email

data class SendMailRequest(
    val templateId: Long,
    val recipient: String,
    val properties: Map<String, String>
)