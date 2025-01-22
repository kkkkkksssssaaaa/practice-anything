package dev.kkkkkksssssaaaa.practice.edaorder.domain.repository

import dev.kkkkkksssssaaaa.practice.edaorder.domain.dto.ProcessPaymentDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "paymentRepository", url = "http://payment-service:8080")
interface PaymentRepository {
    @GetMapping("/payment/users/{userId}/methods/latest")
    fun getPaymentMethod(@PathVariable userId: Long): Map<String, Any>

    @PostMapping("/payment/process")
    fun doProcess(@RequestBody dto: ProcessPaymentDto): Map<String, Any>

    @GetMapping("/payments/{paymentId}")
    fun getPayment(@PathVariable paymentId: Long): Map<String, Any>
}