package dev.kkkkkksssssaaaa.practice.templateserver.domain.email

data class SendNotificationRequest(
    val templateId: Long,
    val target: String,
    val properties: Map<String, String>
)