package dev.kkkkkksssssaaaa.practice.kafka.checkout.dto

data class CheckoutDto(
    var id: Long? = null,
    val memberId: Long,
    val productId: Long,
    val amount: Long,
    val shippingAddress: String,
)