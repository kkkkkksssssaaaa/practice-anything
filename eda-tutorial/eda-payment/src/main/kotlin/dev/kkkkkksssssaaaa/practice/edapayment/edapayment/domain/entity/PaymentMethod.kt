package dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.entity

import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.enums.PaymentMethodType
import jakarta.persistence.*

@Entity
@Table(indexes = [Index(name = "idx_user_id", columnList = "user_id")])
class PaymentMethod(
    userId: Long,
    paymentMethodType: PaymentMethodType,
    cardNumber: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    val userId: Long = userId

    @Column
    val paymentMethodType: PaymentMethodType = paymentMethodType

    @Column
    val cardNumber: String = cardNumber
}