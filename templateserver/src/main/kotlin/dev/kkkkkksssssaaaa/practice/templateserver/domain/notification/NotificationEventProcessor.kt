package dev.kkkkkksssssaaaa.practice.templateserver.domain.notification

import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class NotificationEventProcessor(
    private val emailSender: NotificationSender,
    private val smsSender: NotificationSender,
    private val pushSender: NotificationSender
) {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @EventListener
    fun doProcess(event: NotificationEvent) {
        log.info("Invoke notification event")

        emailSender.doSend(event.message)
        smsSender.doSend(event.message)
        pushSender.doSend(event.message)
    }
}