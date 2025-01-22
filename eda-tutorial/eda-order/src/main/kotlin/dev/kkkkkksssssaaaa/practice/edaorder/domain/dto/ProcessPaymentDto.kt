package dev.kkkkkksssssaaaa.practice.edaorder.domain.dto

data class ProcessPaymentDto(
    val orderId: Long,
    val userId: Long,
    val amountKRW: Long,
    val paymentMethodId: Long,
)