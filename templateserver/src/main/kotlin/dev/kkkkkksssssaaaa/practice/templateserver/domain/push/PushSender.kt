package dev.kkkkkksssssaaaa.practice.templateserver.domain.push

import dev.kkkkkksssssaaaa.practice.templateserver.domain.notification.NotificationEventMessage
import dev.kkkkkksssssaaaa.practice.templateserver.domain.notification.NotificationSender
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class PushSender: NotificationSender {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Async
    override fun doSend(message: NotificationEventMessage) {
        log.info("Push message sent successfully")
    }
}