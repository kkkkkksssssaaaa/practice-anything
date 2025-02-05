package dev.kkkkkksssssaaaa.practice.edacatalog.domain.service

import blackfriday.protobuf.EdaMessage
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.entity.Product
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.entity.SellerProduct
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.repository.ProductRepository
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.repository.SellerProductRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CatalogService(
    private val productRepository: ProductRepository,
    private val sellerProductRepository: SellerProductRepository,
    private val kafkaTemplate: KafkaTemplate<String, ByteArray>
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

        val message = EdaMessage.ProductTags.newBuilder()
            .setProductId(sellerProduct.id!!)
            .addAllTags(tags)
            .build()

        kafkaTemplate.send(
            "product_tags_added",
            message.toByteArray()
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

        val message = EdaMessage.ProductTags.newBuilder()
            .setProductId(product.id)
            .addAllTags(product.tags)
            .build()

        kafkaTemplate.send(
            "product_tags_removed",
            message.toByteArray()
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