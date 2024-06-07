package dev.kkkkkksssssaaaa.practice.templateserver.domain

import dev.kkkkkksssssaaaa.practice.templateserver.domain.email.SendNotificationRequest
import dev.kkkkkksssssaaaa.practice.templateserver.domain.notification.NotificationEvent
import dev.kkkkkksssssaaaa.practice.templateserver.domain.notification.NotificationEventMessage
import dev.kkkkkksssssaaaa.practice.templateserver.domain.template.TemplateRepository
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val eventPublisher: NotificationEventPublisher,
    private val templateRepository: TemplateRepository
) {
    fun send(request: SendNotificationRequest) {
        val template = templateRepository.findById(request.templateId)
            .orElseThrow { IllegalArgumentException("Template not found") }

        eventPublisher.publishEvent(
            NotificationEvent(
                message = NotificationEventMessage(
                    target = request.target,
                    subject = template.name,
                    content = buildContent(template.content, request.properties)
                )
            )
        )
    }

    private fun buildContent(
        templateContent: String,
        properties: Map<String, String>
    ): String {
        var replacedContent = templateContent

        properties.forEach { (key, value) ->
            replacedContent = replacedContent.replace("\${$key}", value)
        }

        return replacedContent
    }
}