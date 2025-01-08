package dev.kkkkkksssssaaaa.practice.edadelivery.domain.repository

import dev.kkkkkksssssaaaa.practice.edadelivery.domain.entity.Delivery
import dev.kkkkkksssssaaaa.practice.edadelivery.domain.enums.DeliveryStatus
import org.springframework.data.jpa.repository.JpaRepository

interface DeliveryRepository: JpaRepository<Delivery, Long> {
    fun findByOrderId(orderId: Long): Delivery

    fun findAllByStatus(status: DeliveryStatus): List<Delivery>
}