package dev.kkkkkksssssaaaa.practice.edacatalog.domain.repository

import dev.kkkkkksssssaaaa.practice.edacatalog.domain.entity.SellerProduct
import org.springframework.data.jpa.repository.JpaRepository

interface SellerProductRepository: JpaRepository<SellerProduct, Long> {
    fun findAllBySellerId(sellerId: Long): List<SellerProduct>
}