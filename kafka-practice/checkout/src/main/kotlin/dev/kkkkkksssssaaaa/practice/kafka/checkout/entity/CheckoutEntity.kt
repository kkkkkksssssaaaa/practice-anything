package dev.kkkkkksssssaaaa.practice.kafka.checkout.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "checkout")
class CheckoutEntity(
    memberId: Long? = null,
    productId: Long? = null,
    amount: Long? = null,
    shippingAddress: String? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

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