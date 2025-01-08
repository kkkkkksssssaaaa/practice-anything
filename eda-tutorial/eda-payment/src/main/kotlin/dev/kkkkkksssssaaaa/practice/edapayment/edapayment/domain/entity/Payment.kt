package dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.entity

import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.enums.PaymentMethodType
import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.enums.PaymentStatus
import jakarta.persistence.*

@Entity
@Table
class Payment(
    userId: Long,
    orderId: Long,
    amountKRW: Long,
    paymentMethodType: PaymentMethodType,
    paymentData: String,
    referenceCode: Long,
    status: PaymentStatus,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    val userId: Long = userId

    @Column(unique = true)
    val orderId: Long = orderId

    @Column
    val amountKWR: Long = amountKRW

    @Column
    val paymentMethodType: PaymentMethodType = paymentMethodType

    @Column
    val paymentData: String = paymentData

    @Column(unique = true)
    val referenceCode: Long = referenceCode

    @Column
    val status: PaymentStatus = status
}