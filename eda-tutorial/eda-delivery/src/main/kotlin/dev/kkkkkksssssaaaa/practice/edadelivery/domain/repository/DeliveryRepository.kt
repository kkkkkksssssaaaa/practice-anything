package dev.kkkkkksssssaaaa.practice.edadelivery.domain.repository

import dev.kkkkkksssssaaaa.practice.edadelivery.domain.entity.Delivery
import org.springframework.data.jpa.repository.JpaRepository

interface DeliveryRepository: JpaRepository<Delivery, Long> {
    fun findByOrderId(orderId: Long): Delivery
}