package dev.kkkkkksssssaaaa.practice.edaorder.domain.dto

data class ProcessDeliveryDto(
    val orderId: Long,
    val productName: String,
    val productCount: Long,
    val address: String
)
