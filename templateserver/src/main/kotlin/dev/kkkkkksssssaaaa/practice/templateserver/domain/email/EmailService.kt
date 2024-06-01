package dev.kkkkkksssssaaaa.practice.templateserver.domain.email

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender,
    private val contentBuilder: MailContentBuilder,
) {
    fun sendEmail(
        request: SendMailRequest
    ) {
        val content = contentBuilder.build(request)
        javaMailSender.send(content)
    }
}
