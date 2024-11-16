package dev.kkkkkksssssaaaa.practice.kafka.checkout.service

import com.fasterxml.jackson.databind.ObjectMapper
import dev.kkkkkksssssaaaa.practice.kafka.checkout.dto.CheckoutDto
import dev.kkkkkksssssaaaa.practice.kafka.checkout.entity.CheckoutEntity
import dev.kkkkkksssssaaaa.practice.kafka.checkout.repository.CheckoutRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SaveService(
    private val checkoutRepository: CheckoutRepository,
    private val objectMapper: ObjectMapper,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    companion object {
        private const val CHECKOUT_COMPLETE_TOPIC = "kafka-practice-checkout-complete"
    }

    @Transactional
    fun save(dto: CheckoutDto): Long {
        val savedEntity = checkoutRepository.saveAndFlush(
            CheckoutEntity(
                memberId = dto.memberId,
                productId = dto.productId,
                amount = dto.amount,
                shippingAddress = dto.shippingAddress,
            )
        )

        publishToKafka(dto)

        return savedEntity.id!!
    }

    private fun publishToKafka(dto: CheckoutDto) {
        val toJsonString = objectMapper.writeValueAsString(dto)
        kafkaTemplate.send(CHECKOUT_COMPLETE_TOPIC, toJsonString)

        println("success publish to kafka")
    }
}