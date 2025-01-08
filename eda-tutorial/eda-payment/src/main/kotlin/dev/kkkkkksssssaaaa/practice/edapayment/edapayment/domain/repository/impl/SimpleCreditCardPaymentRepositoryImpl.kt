package dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.repository.impl

import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.repository.CreditCardPaymentRepository
import org.springframework.stereotype.Service

@Service
class SimpleCreditCardPaymentRepositoryImpl: CreditCardPaymentRepository {
    override fun process(amountKRW: Long, cardNumber: String): Long {
        return Math.round(Math.random() * 10000000)
    }
}