package dev.kkkkkksssssaaaa.practice.templateserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class TemplateServerApplication

fun main(args: Array<String>) {
    runApplication<TemplateServerApplication>(*args)
}
