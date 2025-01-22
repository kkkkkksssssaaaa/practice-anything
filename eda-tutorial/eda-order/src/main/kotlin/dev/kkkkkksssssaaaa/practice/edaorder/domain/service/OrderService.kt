package dev.kkkkkksssssaaaa.practice.edaorder.domain.service

import dev.kkkkkksssssaaaa.practice.edaorder.domain.dto.*
import dev.kkkkkksssssaaaa.practice.edaorder.domain.entity.ProductOrder
import dev.kkkkkksssssaaaa.practice.edaorder.domain.enums.OrderStatus
import dev.kkkkkksssssaaaa.practice.edaorder.domain.repository.CatalogRepository
import dev.kkkkkksssssaaaa.practice.edaorder.domain.repository.DeliveryRepository
import dev.kkkkkksssssaaaa.practice.edaorder.domain.repository.PaymentRepository
import dev.kkkkkksssssaaaa.practice.edaorder.domain.repository.ProductOrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val repository: ProductOrderRepository,
    private val payment: PaymentRepository,
    private val delivery: DeliveryRepository,
    private val catalog: CatalogRepository
) {
    @Transactional
    fun doStart(
        userId: Long,
        productId: Long,
        count: Long
    ): StartOrderResponse {
        val product = catalog.get(productId)
        val paymentMethod = payment.getPaymentMethod(userId)
        val address = delivery.getAddress(userId)

        val order = repository.save(
            ProductOrder(
                userId = userId,
                productId = productId,
                count = count,
                status = OrderStatus.INITIATED,
            )
        )

        return StartOrderResponse(
            orderId = order.id!!,
            paymentMethod = paymentMethod,
            address = address,
        )
    }

    @Transactional
    fun finish(
        orderId: Long,
        paymentMethodId: Long,
        addressId: Long
    ): ProductOrder {
        val order = repository.findById(orderId)
            .orElseThrow { throw IllegalArgumentException() }

        val product = catalog.get(order.productId)
        val address = delivery.getAddress(order.userId)

        val paymentResult = payment.doProcess(
            ProcessPaymentDto(
                orderId = order.id!!,
                paymentMethodId = paymentMethodId,
                amountKRW = product["price"].toString().toLong(),
                userId = order.userId,
            )
        )

        val deliveryResult = delivery.doProcess(
            ProcessDeliveryDto(
                orderId = order.id!!,
                productName = product["name"].toString(),
                productCount = order.count,
                address = address["address"].toString(),
            )
        )

        catalog.decrease(order.productId, DecreaseStockCountDto(order.count))

        order.deliveryId = deliveryResult["id"] as Long
        order.paymentId = paymentResult["id"] as Long
        order.status = OrderStatus.DELIVERY_REQUESTED

        return repository.save(order)
    }

    @Transactional(readOnly = true)
    fun findAllByUser(userId: Long): List<ProductOrder> {
        return repository.findAllByUserId(userId)
    }

    @Transactional(readOnly = true)
    fun find(id: Long): ProductOrderDetailDto {
        val order = repository.findById(id)
            .orElseThrow { throw IllegalArgumentException() }
        val payment = payment.getPayment(order.paymentId!!)
        val delivery = delivery.getDelivery(order.deliveryId!!)

        return ProductOrderDetailDto(
            id = order.id!!,
            userId = order.userId,
            productId = order.productId,
            paymentId = payment["id"] as Long,
            deliveryId = delivery["id"] as Long,
            orderStatus = order.status,
            paymentStatus = payment["status"].toString(),
            deliveryStatus = delivery["status"].toString(),
        )
    }
}