package dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.repository

import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.entity.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository: JpaRepository<Payment, Long> {
}