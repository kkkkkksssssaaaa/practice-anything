package dev.kkkkkksssssaaaa.practice.templateserver.domain.email

import dev.kkkkkksssssaaaa.practice.templateserver.domain.notification.NotificationEventMessage
import dev.kkkkkksssssaaaa.practice.templateserver.domain.notification.NotificationSender
import org.slf4j.LoggerFactory
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class EmailSender(
    private val javaMailSender: JavaMailSender,
    private val contentBuilder: MailContentBuilder,
): NotificationSender {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Async
    override fun doSend(message: NotificationEventMessage) {
        val mailContent = contentBuilder.build(
            target = message.target,
            subject = message.subject,
            content = message.content
        )

//        javaMailSender.send(mailContent)

        log.info("Email sent successfully")
    }
}
