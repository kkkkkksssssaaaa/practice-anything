package dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.dto

data class CreatePaymentDto(
    val userId: Long,
    val orderId: Long,
    val amountKRW: Long,
    val paymentMethodId: Long,
)