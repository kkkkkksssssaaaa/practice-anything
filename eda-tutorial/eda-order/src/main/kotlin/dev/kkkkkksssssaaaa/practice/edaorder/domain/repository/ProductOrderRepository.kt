package dev.kkkkkksssssaaaa.practice.edaorder.domain.repository

import dev.kkkkkksssssaaaa.practice.edaorder.domain.entity.ProductOrder
import org.springframework.data.jpa.repository.JpaRepository

interface ProductOrderRepository: JpaRepository<ProductOrder, Long> {
    fun findAllByUserId(userId: Long): List<ProductOrder>
}