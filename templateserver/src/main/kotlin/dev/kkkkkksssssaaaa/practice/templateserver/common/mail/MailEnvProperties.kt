package dev.kkkkkksssssaaaa.practice.templateserver.common.mail

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MailEnvProperties(
    @Value("\${app.mail.encoding}") val encoding: String,
    @Value("\${app.mail.host}") val host: String,
    @Value("\${app.mail.port}") val port: String,
    @Value("\${app.mail.username}") val username: String,
    @Value("\${app.mail.password}") val password: String,
)