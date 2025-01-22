package dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.controller

import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.dto.CreatePaymentDto
import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.dto.CreatePaymentMethodDto
import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.entity.Payment
import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.entity.PaymentMethod
import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.service.PaymentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payment")
class PaymentController(
    private val service: PaymentService
) {
    @PostMapping("/methods")
    fun registrationPaymentMethod(
        @RequestBody dto: CreatePaymentMethodDto
    ): ResponseEntity<PaymentMethod> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                service.registrationPaymentMethod(
                    dto.userId,
                    dto.paymentMethodType,
                    dto.cardNumber
                )
            )
    }

    @PostMapping("/process")
    fun doProcess(
        @RequestBody dto: CreatePaymentDto
    ): ResponseEntity<Payment> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                service.registrationPayment(
                    dto.userId,
                    dto.orderId,
                    dto.amountKRW,
                    dto.paymentMethodId
                )
            )
    }

    @GetMapping("/users/{userId}/methods/latest")
    fun getLatestPaymentMethod(
        @PathVariable userId: Long,
    ): ResponseEntity<PaymentMethod> {
        return ResponseEntity.ok(
            service.getPaymentMethod(userId)
        )
    }

    @GetMapping("/payments/{paymentId}")
    fun getPayment(
        @PathVariable paymentId: Long,
    ): ResponseEntity<Payment> {
        return ResponseEntity.ok(
            service.getPayment(paymentId)
        )
    }
}