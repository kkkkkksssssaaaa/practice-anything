package dev.kkkkkksssssaaaa.practice.templateserver.domain.email

import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

@Component
class MailContentBuilder(
    private val javaMailSender: JavaMailSender,
) {
    fun build(
        target: String,
        subject: String,
        content: String
    ): MimeMessage {
        val message = javaMailSender.createMimeMessage()

        val helper = MimeMessageHelper(message, true)

        helper.setTo(target)
        helper.setSubject(subject)
        helper.setText(content, true)

        return message
    }
}