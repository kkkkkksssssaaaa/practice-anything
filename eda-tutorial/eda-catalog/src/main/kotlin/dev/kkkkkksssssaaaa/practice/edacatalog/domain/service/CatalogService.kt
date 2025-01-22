package dev.kkkkkksssssaaaa.practice.edacatalog.domain.service

import dev.kkkkkksssssaaaa.practice.edacatalog.domain.dto.TagsDto
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.entity.Product
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.entity.SellerProduct
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.repository.ProductRepository
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.repository.SearchRepository
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.repository.SellerProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CatalogService(
    private val productRepository: ProductRepository,
    private val sellerProductRepository: SellerProductRepository,
    private val searchRepository: SearchRepository
) {
    @Transactional
    fun registration(
        sellerId: Long,
        name: String,
        description: String,
        price: Long,
        stockCount: Long,
        tags: List<String>,
    ): Product {
        val sellerProduct = sellerProductRepository.saveAndFlush(
            SellerProduct(
                sellerId = sellerId,
            )
        )

        searchRepository.addTagCache(
            TagsDto(
                productId = sellerProduct.id!!,
                tags = tags,
            )
        )

        return productRepository.save(
            Product(
                id = sellerProduct.id!!,
                name = name,
                description = description,
                price = price,
                stockCount = stockCount,
                tags = tags,
                sellerId = sellerId
            )
        )
    }

    @Transactional
    fun delete(id: Long) {
        val product = productRepository.findById(id)
            .orElseThrow {
                throw IllegalArgumentException()
            }

        searchRepository.removeTags(
            TagsDto(
                productId = product.id,
                tags = product.tags,
            )
        )

        productRepository.deleteById(id)
        sellerProductRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    fun findAllBySellerId(sellerId: Long): List<Product> {
        return sellerProductRepository.findAllBySellerId(sellerId)
            .map {
                productRepository.findById(it.id!!)
                    .orElseThrow {
                        throw IllegalArgumentException()
                    }
            }
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): Product {
        return productRepository.findById(id)
            .orElseThrow {
                throw IllegalArgumentException()
            }
    }

    @Transactional
    fun decreaseStockCount(id: Long, decreaseCount: Long): Product {
        val findProduct = productRepository.findById(id)
            .orElseThrow {
                throw IllegalArgumentException()
            }

        findProduct.stockCount -= decreaseCount

        return productRepository.save(findProduct)
    }
}