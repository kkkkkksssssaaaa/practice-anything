package dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.service

import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.entity.Payment
import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.entity.PaymentMethod
import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.enums.PaymentMethodType
import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.enums.PaymentStatus
import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.repository.CreditCardPaymentRepository
import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.repository.PaymentMethodRepository
import dev.kkkkkksssssaaaa.practice.edapayment.edapayment.domain.repository.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentMethodRepository: PaymentMethodRepository,
    private val creditCardPaymentRepository: CreditCardPaymentRepository
) {
    @Transactional
    fun registrationPaymentMethod(
        userId: Long,
        paymentMethodType: PaymentMethodType,
        cardNumber: String
    ): PaymentMethod {
        val paymentMethod = PaymentMethod(
            userId,
            paymentMethodType,
            cardNumber,
        )

        return paymentMethodRepository.save(paymentMethod)
    }

    @Transactional
    fun registrationPayment(
        userId: Long,
        orderId: Long,
        amountKRW: Long,
        paymentMethodId: Long,
    ): Payment {
        val paymentMethod = paymentMethodRepository.findById(paymentMethodId)
            .orElseThrow { IllegalArgumentException() }

        if (paymentMethod.paymentMethodType != PaymentMethodType.CREDIT_CARD) {
            throw IllegalArgumentException()
        }

        val referenceCode = creditCardPaymentRepository.process(
            amountKRW,
            paymentMethod.cardNumber
        )

        val payment = Payment(
            userId = userId,
            orderId = orderId,
            amountKRW = amountKRW,
            paymentMethodType = paymentMethod.paymentMethodType,
            referenceCode = referenceCode,
            status = PaymentStatus.COMPLETED,
            paymentData = paymentMethod.cardNumber
        )

        return paymentRepository.save(payment)
    }

    @Transactional(readOnly = true)
    fun getPaymentMethod(userId: Long): PaymentMethod {
        return paymentMethodRepository.findById(userId)
            .orElseThrow { IllegalArgumentException() }
    }

    @Transactional(readOnly = true)
    fun getPayment(paymentId: Long): Payment {
        return paymentRepository.findById(paymentId)
            .orElseThrow { IllegalArgumentException() }
    }
}