package dev.kkkkkksssssaaaa.practice.edacatalog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class EdaCatalogApplication

fun main(args: Array<String>) {
    runApplication<EdaCatalogApplication>(*args)
}
