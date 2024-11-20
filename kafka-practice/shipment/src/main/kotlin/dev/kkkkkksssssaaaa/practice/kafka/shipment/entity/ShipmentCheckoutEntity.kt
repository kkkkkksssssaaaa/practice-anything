package dev.kkkkkksssssaaaa.practice.kafka.shipment.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "shipment_checkout")
class ShipmentCheckoutEntity(
    checkoutId: Long? = null,
    memberId: Long? = null,
    productId: Long? = null,
    amount: Long? = null,
    shippingAddress: String? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    var checkoutId: Long? = checkoutId

    @Column
    var memberId: Long? = memberId

    @Column
    val productId: Long? = productId

    @Column
    val amount: Long? = amount

    @Column
    val shippingAddress: String? = shippingAddress

    @Column
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()
}