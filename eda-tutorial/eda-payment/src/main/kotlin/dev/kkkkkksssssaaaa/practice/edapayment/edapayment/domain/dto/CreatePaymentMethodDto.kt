package dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.dto

import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.enums.PaymentMethodType

data class CreatePaymentMethodDto(
    val userId: Long,
    val paymentMethodType: PaymentMethodType,
    val cardNumber: String
)