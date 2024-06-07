package dev.kkkkkksssssaaaa.practice.templateserver.domain.notification

import org.springframework.context.ApplicationEvent

data class NotificationEvent(
    val message: NotificationEventMessage
): ApplicationEvent(message)