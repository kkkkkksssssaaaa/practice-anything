package dev.kkkkkksssssaaaa.practice.templateserver.domain

data class SendMailRequest(
    val templateId: Long,
    val recipient: String,
    val properties: Map<String, String>
)