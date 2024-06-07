package dev.kkkkkksssssaaaa.practice.templateserver.domain

import dev.kkkkkksssssaaaa.practice.templateserver.domain.notification.NotificationEvent
import dev.kkkkkksssssaaaa.practice.templateserver.domain.notification.NotificationEventProcessor
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class NotificationEventPublisher(
    private val eventProcessor: NotificationEventProcessor
): ApplicationEventPublisher {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun publishEvent(event: Any) {
        log.info("Publish notification event")
        eventProcessor.doProcess(event as NotificationEvent)
    }
}