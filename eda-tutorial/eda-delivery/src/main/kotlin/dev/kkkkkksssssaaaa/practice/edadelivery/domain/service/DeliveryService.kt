package dev.kkkkkksssssaaaa.practice.edadelivery.domain.service

import dev.kkkkkksssssaaaa.practice.edadelivery.domain.entity.Delivery
import dev.kkkkkksssssaaaa.practice.edadelivery.domain.entity.UserAddress
import dev.kkkkkksssssaaaa.practice.edadelivery.domain.enums.DeliveryStatus
import dev.kkkkkksssssaaaa.practice.edadelivery.domain.repository.DeliveryAdapter
import dev.kkkkkksssssaaaa.practice.edadelivery.domain.repository.DeliveryRepository
import dev.kkkkkksssssaaaa.practice.edadelivery.domain.repository.UserAddressRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeliveryService(
    private val deliveryRepository: DeliveryRepository,
    private val userAddressRepository: UserAddressRepository,
    private val adapter: DeliveryAdapter
) {
    @Transactional
    fun addUserAddress(
        userId: Long,
        address: String,
        alias: String
    ): UserAddress {
        val userAddress = UserAddress(
            userId, address, alias
        )

        return userAddressRepository.save(userAddress)
    }

    @Transactional
    fun processDelivery(
        orderId: Long,
        productName: String,
        count: Int,
        address: String
    ): Delivery {
        val referenceCode = adapter.process(productName, count, address)

        val delivery = Delivery(
            orderId = orderId,
            productName = productName,
            count = count,
            address = address,
            status = DeliveryStatus.REQUESTED,
            referenceCode = referenceCode
        )

        return deliveryRepository.save(delivery)
    }

    @Transactional(readOnly = true)
    fun getDelivery(deliveryId: Long): Delivery {
        return deliveryRepository.findById(deliveryId)
            .orElseThrow { IllegalArgumentException() }
    }

    @Transactional(readOnly = true)
    fun getAddress(addressId: Long): UserAddress {
        return userAddressRepository.findById(addressId)
            .orElseThrow { IllegalArgumentException() }
    }

    @Transactional(readOnly = true)
    fun getAddressByUserId(userId: Long): UserAddress {
        return userAddressRepository.findAllByUserId(userId)
            .firstOrNull() ?: throw IllegalArgumentException()
    }
}