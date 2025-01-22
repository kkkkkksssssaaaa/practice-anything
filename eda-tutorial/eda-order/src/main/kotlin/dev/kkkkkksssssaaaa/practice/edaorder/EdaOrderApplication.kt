package dev.kkkkkksssssaaaa.practice.edaorder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class EdaOrderApplication

fun main(args: Array<String>) {
	runApplication<EdaOrderApplication>(*args)
}
