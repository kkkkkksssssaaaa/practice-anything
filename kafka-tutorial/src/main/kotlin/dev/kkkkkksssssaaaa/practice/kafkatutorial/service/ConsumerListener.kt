package dev.kkkkkksssssaaaa.practice.kafkatutorial.service

import com.fasterxml.jackson.databind.ObjectMapper
import dev.kkkkkksssssaaaa.practice.kafkatutorial.dto.MessageDto
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private const val TOPIC_NAME = "topic-example-3"

@Component
class ConsumerListener(
    private val objectMapper: ObjectMapper,
) {
    @KafkaListener(topics = [TOPIC_NAME])
    fun listen(message: String) {
        try {
            val message = objectMapper.readValue(message, MessageDto::class.java)
            println("Received message: $message")
        } catch (e: Exception) {}
    }
}