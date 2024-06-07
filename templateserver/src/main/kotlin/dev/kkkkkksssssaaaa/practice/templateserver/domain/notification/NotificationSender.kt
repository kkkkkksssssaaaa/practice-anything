package dev.kkkkkksssssaaaa.practice.templateserver.domain.notification

interface NotificationSender {
    fun doSend(message: NotificationEventMessage)
}