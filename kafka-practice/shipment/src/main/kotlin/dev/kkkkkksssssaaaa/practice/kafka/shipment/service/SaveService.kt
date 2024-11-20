package dev.kkkkkksssssaaaa.practice.kafka.shipment.service

import dev.kkkkkksssssaaaa.practice.kafka.shipment.dto.CheckoutDto
import dev.kkkkkksssssaaaa.practice.kafka.shipment.entity.ShipmentCheckoutEntity
import dev.kkkkkksssssaaaa.practice.kafka.shipment.repository.ShipmentCheckoutRepository
import org.springframework.stereotype.Service

@Service
class SaveService(
    private val repository: ShipmentCheckoutRepository
) {
    fun save(dto: CheckoutDto): Long {
        val savedEntity = repository.saveAndFlush(
            ShipmentCheckoutEntity(
                checkoutId = dto.id,
                memberId = dto.memberId,
                productId = dto.productId,
                amount = dto.amount,
                shippingAddress = dto.shippingAddress,
            )
        )

        return savedEntity.id!!
    }
}