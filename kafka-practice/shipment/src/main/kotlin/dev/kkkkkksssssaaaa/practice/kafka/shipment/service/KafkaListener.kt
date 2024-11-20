package dev.kkkkkksssssaaaa.practice.kafka.shipment.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.kkkkkksssssaaaa.practice.kafka.shipment.dto.CheckoutDto
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private const val TOPIC_NAME = "kafka-practice-checkout-complete"
private const val GROUP_ID = "kafka-practice-checkout-complete-group-1"

@Component
class KafkaListener(
    private val saveService: SaveService
) {
    private val objectMapper = jacksonObjectMapper()

    init {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    @KafkaListener(topics = [TOPIC_NAME], groupId = GROUP_ID)
    fun recordListListener(jsonMessage: String) {
        try {
            val checkoutDto = objectMapper.readValue(jsonMessage, CheckoutDto::class.java)
            println(checkoutDto)

            saveService.save(checkoutDto)
        } catch (ex: Exception) {
            println("recordListListener: $jsonMessage $ex")
        }
    }
}