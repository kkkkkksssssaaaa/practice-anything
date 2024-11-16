package dev.kkkkkksssssaaaa.practice.kafka.checkout.dto

data class CheckoutDto(
    val memberId: Long,
    val productId: Long,
    val amount: Long,
    val shippingAddress: String,
)