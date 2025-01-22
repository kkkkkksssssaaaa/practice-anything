package dev.kkkkkksssssaaaa.practice.edadelivery.domain.controller

import dev.kkkkkksssssaaaa.practice.edadelivery.domain.dto.ProcessDeliveryDto
import dev.kkkkkksssssaaaa.practice.edadelivery.domain.dto.RegistrationUserAddressDto
import dev.kkkkkksssssaaaa.practice.edadelivery.domain.entity.Delivery
import dev.kkkkkksssssaaaa.practice.edadelivery.domain.entity.UserAddress
import dev.kkkkkksssssaaaa.practice.edadelivery.domain.service.DeliveryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/delivery")
class DeliveryController(
    private val service: DeliveryService,
) {
    @PostMapping("/users/{userId}/address")
    fun registrationUserAddress(
        @RequestBody dto: RegistrationUserAddressDto
    ): ResponseEntity<UserAddress> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                service.addUserAddress(
                    userId = dto.userId,
                    address = dto.address,
                    alias = dto.alias
                )
            )
    }

    @PostMapping("/process")
    fun doProcess(
        @RequestBody dto: ProcessDeliveryDto
    ): ResponseEntity<Delivery> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                service.processDelivery(
                    orderId = dto.orderId,
                    productName = dto.productName,
                    count = dto.count,
                    address = dto.address,
                )
            )
    }

    @GetMapping("/{deliveryId}")
    fun getDelivery(@PathVariable deliveryId: Long): ResponseEntity<Delivery> {
        return ResponseEntity.ok(
            service.getDelivery(deliveryId)
        )
    }

    @GetMapping("/users/{userId}/address")
    fun getAddressOfUser(@PathVariable userId: Long): ResponseEntity<UserAddress> {
        return ResponseEntity.ok(
            service.getAddressByUserId(userId)
        )
    }

    @GetMapping("/address/{addressId}")
    fun getAddress(
        @PathVariable addressId: Long
    ): ResponseEntity<UserAddress> {
        return ResponseEntity.ok(
            service.getAddress(addressId)
        )
    }
}