package dev.kkkkkksssssaaaa.practice.kafkatutorial.service

import dev.kkkkkksssssaaaa.practice.kafkatutorial.dto.MessageDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class ProducerService(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val kafkaTemplate2: KafkaTemplate<String, MessageDto>,
) {
    private val TOPIC_NAME = "topic-example-3"

    fun publish(message: String) {
        kafkaTemplate.send(TOPIC_NAME, message)
    }

    fun publishWithCallback(message: String) {
        val future: CompletableFuture<SendResult<String, String>> =
            kafkaTemplate.send(TOPIC_NAME, message)

        future.whenComplete { result, exception ->
            result?.let {
                println("Sent $message offset: ${result.recordMetadata.offset()}")
            }

            exception?.let {
                println("Failed $message due to exception: ${it.message}")
            }
        }
    }

    fun publishJson(message: MessageDto) {
        kafkaTemplate2.send(TOPIC_NAME, message)
    }
}