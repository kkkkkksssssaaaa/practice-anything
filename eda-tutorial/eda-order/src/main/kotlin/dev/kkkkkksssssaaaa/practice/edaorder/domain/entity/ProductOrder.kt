package dev.kkkkkksssssaaaa.practice.edaorder.domain.entity

import dev.kkkkkksssssaaaa.practice.edaorder.domain.enums.OrderStatus
import jakarta.persistence.*

@Entity
class ProductOrder(
    userId: Long,
    productId: Long,
    count: Long,
    status: OrderStatus,
    paymentId: Long? = null,
    deliveryId: Long? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    val userId: Long = userId

    @Column
    val productId: Long = productId

    @Column
    val count: Long = count

    @Column
    @Enumerated(EnumType.STRING)
    var status: OrderStatus = status

    @Column
    var paymentId: Long? = paymentId

    @Column
    var deliveryId: Long? = deliveryId
}