package dev.kkkkkksssssaaaa.practice.edaorder.domain.dto

data class FinishOrderDto(
    val orderId: Long,
    val paymentMethodId: Long,
    val addressId: Long
)
