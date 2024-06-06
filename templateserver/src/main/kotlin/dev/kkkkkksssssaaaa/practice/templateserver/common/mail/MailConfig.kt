package dev.kkkkkksssssaaaa.practice.templateserver.common.mail

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

@Configuration
class MailConfig(
    private val env: MailEnvProperties
) {
    @Bean
    fun javaMailService(): JavaMailSender {
        val sender = JavaMailSenderImpl()

        sender.host = env.host
        sender.username = env.username
        sender.password = env.password
        sender.defaultEncoding = env.encoding

        sender.port = env.port.toInt()

        sender.javaMailProperties = customMailProperties()

        return sender
    }

    @Bean
    fun customMailProperties(): Properties {
        val properties = Properties()

        properties.setProperty("mail.transport.protocol", "smtp")
        properties.setProperty("mail.smtp.auth", "true")
        properties.setProperty("mail.smtp.starttls.enable", "true")
        properties.setProperty("mail.debug", "true")
        properties.setProperty("mail.smtp.ssl.trust", env.host)
        properties.setProperty("mail.smtp.ssl.enable", "true")

        return properties
    }
}