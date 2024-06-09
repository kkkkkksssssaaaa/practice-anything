package dev.kkkkkksssssaaaa.practice.templateserver

import jakarta.persistence.EntityListeners
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AdviceMode
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync(mode = AdviceMode.ASPECTJ)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EntityListeners(AuditingEntityListener::class)
@SpringBootApplication
class TemplateServerApplication

fun main(args: Array<String>) {
    runApplication<TemplateServerApplication>(*args)
}
