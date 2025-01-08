package dev.kkkkkksssssaaaa.practice.edadelivery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class EdaDeliveryApplication

fun main(args: Array<String>) {
	runApplication<EdaDeliveryApplication>(*args)
}
