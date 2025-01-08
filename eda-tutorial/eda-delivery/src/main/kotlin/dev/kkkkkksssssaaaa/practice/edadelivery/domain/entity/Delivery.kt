package dev.kkkkkksssssaaaa.practice.edadelivery.domain.entity

import dev.kkkkkksssssaaaa.practice.edadelivery.domain.enums.DeliveryStatus
import jakarta.persistence.*

@Entity
@Table(indexes = [Index(name = "order_id", columnList = "order_id")])
class Delivery(
    orderId: Long,
    productName: String,
    count: Int,
    address: String,
    referenceCode: Long,
    status: DeliveryStatus
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    val orderId: Long = orderId

    @Column
    val productName: String = productName

    @Column
    val count: Int = count

    @Column
    val address: String = address

    @Column
    val referenceCode: Long = referenceCode

    @Column
    @Enumerated(EnumType.STRING)
    val status: DeliveryStatus = status
}