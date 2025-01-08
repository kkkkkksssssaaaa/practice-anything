package dev.kkkkkksssssaaaa.practice.edadelivery.domain.dto

data class ProcessDeliveryDto(
    val orderId: Long,
    val productName: String,
    val count: Int,
    val address: String
)