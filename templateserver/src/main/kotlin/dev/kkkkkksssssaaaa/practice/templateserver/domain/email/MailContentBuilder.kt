package dev.kkkkkksssssaaaa.practice.templateserver.domain.email

import dev.kkkkkksssssaaaa.practice.templateserver.domain.template.TemplateRepository
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

@Component
class MailContentBuilder(
    private val javaMailSender: JavaMailSender,
    private val templateRepository: TemplateRepository,
) {
    fun build(
        request: SendMailRequest
    ): MimeMessage {
        val template = templateRepository.findById(request.templateId)
            .orElseThrow { IllegalArgumentException("Template not found") }

        var content = template.content

        request.properties.forEach { (key, value) ->
            content = content.replace("{{ $key }}", value)
        }

        val message = javaMailSender.createMimeMessage()

        val helper = MimeMessageHelper(message, true)

        helper.setTo(request.target)
        helper.setSubject(template.name)
        helper.setText(content, true)

        return message
    }
}