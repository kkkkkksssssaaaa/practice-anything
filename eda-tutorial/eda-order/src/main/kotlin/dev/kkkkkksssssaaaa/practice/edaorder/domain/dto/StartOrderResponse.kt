package dev.kkkkkksssssaaaa.practice.edaorder.domain.dto

data class StartOrderResponse(
    val orderId: Long,
    val paymentMethod: Map<String, Any>,
    val address: Map<String, Any>
)