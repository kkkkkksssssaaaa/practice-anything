package dev.kkkkkksssssaaaa.practice.templateserver.domain.email

import dev.kkkkkksssssaaaa.practice.templateserver.domain.template.CustomPropertyRepository
import dev.kkkkkksssssaaaa.practice.templateserver.domain.template.TemplateRepository
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender,
    private val templateRepository: TemplateRepository,
    private val customPropertyRepository: CustomPropertyRepository
) {

    fun sendEmail(templateId: Long, recipient: String, properties: Map<String, String>) {
        val template = templateRepository.findById(templateId).orElseThrow { IllegalArgumentException("Template not found") }
        var content = template.content

        properties.forEach { (key, value) ->
            content = content.replace("{{ $key }}", value)
        }

        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)
        helper.setTo(recipient)
        helper.setSubject("Subject")
        helper.setText(content, true)

        javaMailSender.send(message)
    }
}
