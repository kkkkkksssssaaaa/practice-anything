package dev.kkkkkksssssaaaa.practice.kafkatutorial.controller

import dev.kkkkkksssssaaaa.practice.kafkatutorial.dto.MessageDto
import dev.kkkkkksssssaaaa.practice.kafkatutorial.service.ProducerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProducerController(
    private val producerService: ProducerService
) {
    @GetMapping("/publish")
    fun doPublish(message: String): String {
        producerService.publish(message)

        return "published a message: $message"
    }

    @GetMapping("/publish/callback")
    fun doPublishWithCallback(message: String): String {
        producerService.publishWithCallback(message)

        return "published a message: $message"
    }

    @PostMapping("/publish/v2")
    fun doPublishV2(@RequestBody message: MessageDto): String {
        producerService.publishJson(message)

        return "published a message: $message"
    }
}