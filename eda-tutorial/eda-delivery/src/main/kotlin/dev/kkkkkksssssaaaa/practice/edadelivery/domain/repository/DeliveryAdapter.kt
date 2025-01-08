package dev.kkkkkksssssaaaa.practice.edadelivery.domain.repository

interface DeliveryAdapter {
    fun process(productName: String, count: Int, address: String): Long
}