package dev.kkkkkksssssaaaa.practice.kafka.shipment.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class CheckoutDto @JsonCreator constructor(
    val id: Long? = null,
    val memberId: Long,
    val productId: Long,
    val amount: Long,
    val shippingAddress: String,
)