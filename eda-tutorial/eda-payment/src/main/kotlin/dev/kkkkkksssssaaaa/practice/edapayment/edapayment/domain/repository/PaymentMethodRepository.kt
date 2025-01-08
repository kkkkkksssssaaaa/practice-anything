package dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.repository

import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.entity.PaymentMethod
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentMethodRepository: JpaRepository<PaymentMethod, Long> {
    fun findByUserId(userId: Long): List<PaymentMethod>
}