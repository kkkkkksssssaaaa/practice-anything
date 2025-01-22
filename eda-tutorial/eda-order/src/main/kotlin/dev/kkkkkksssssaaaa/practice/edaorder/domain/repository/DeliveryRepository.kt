package dev.kkkkkksssssaaaa.practice.edaorder.domain.repository

import dev.kkkkkksssssaaaa.practice.edaorder.domain.dto.ProcessDeliveryDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "deliveryRepository", url = "http://delivery-service:8080")
interface DeliveryRepository {
    @GetMapping("/delivery/users/{userId}/address")
    fun get(@PathVariable userId: Long): Map<String, Any>

    @GetMapping("/delivery/address/{addressId}")
    fun getAddress(@PathVariable addressId: Long): Map<String, Any>

    @PostMapping("/delivery/process")
    fun doProcess(@RequestBody dto: ProcessDeliveryDto): Map<String, Any>

    @GetMapping("/delivery/{deliveryId}")
    fun getDelivery(@PathVariable deliveryId: Long): Map<String, Any>
}
