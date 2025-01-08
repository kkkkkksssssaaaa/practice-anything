package dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.repository

interface CreditCardPaymentRepository {
    fun process(
        amountKRW: Long,
        cardNumber: String
    ): Long
}