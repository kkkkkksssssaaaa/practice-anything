package dev.kkkkkksssssaaaa.practice.edaorder.domain.controller

import dev.kkkkkksssssaaaa.practice.edaorder.domain.dto.FinishOrderDto
import dev.kkkkkksssssaaaa.practice.edaorder.domain.dto.ProductOrderDetailDto
import dev.kkkkkksssssaaaa.practice.edaorder.domain.dto.StartOrderResponse
import dev.kkkkkksssssaaaa.practice.edaorder.domain.dto.StartOrderdto
import dev.kkkkkksssssaaaa.practice.edaorder.domain.entity.ProductOrder
import dev.kkkkkksssssaaaa.practice.edaorder.domain.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController(
    private val service: OrderService
) {
    @PostMapping
    fun doOrder(
        @RequestBody dto: StartOrderdto
    ): ResponseEntity<StartOrderResponse> {
        return ResponseEntity.ok(
            service.doStart(
                userId = dto.userId,
                productId = dto.productId,
                count = dto.count
            )
        )
    }

    @PostMapping("/finish")
    fun doFinish(
        @RequestBody dto: FinishOrderDto
    ): ResponseEntity<ProductOrder> {
        return ResponseEntity.ok(
            service.finish(
                orderId = dto.orderId,
                paymentMethodId = dto.paymentMethodId,
                addressId = dto.addressId,
            )
        )
    }

    @GetMapping("/users/{userId}/orders")
    fun getUserOrders(
        @PathVariable userId: Long,
    ): ResponseEntity<List<ProductOrder>> {
        return ResponseEntity.ok(
            service.findAllByUser(userId)
        )
    }

    @GetMapping("/orders/{orderId}")
    fun get(
        @PathVariable orderId: Long,
    ): ResponseEntity<ProductOrderDetailDto> {
        return ResponseEntity.ok(
            service.find(orderId)
        )
    }
}