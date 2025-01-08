package dev.kkkkkksssssaaaa.practice.edadelivery.domain.repository

import org.springframework.stereotype.Service

@Service
class SimpleDeliveryAdapterImpl : DeliveryAdapter {
    override fun process(
        productName: String,
        count: Int,
        address: String
    ): Long {
        return 1L
    }
}