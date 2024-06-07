package dev.kkkkkksssssaaaa.practice.templateserver.domain.notification

data class NotificationEventMessage(
    val target: String,
    val subject: String,
    val content: String,
)