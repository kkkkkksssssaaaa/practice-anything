package dev.kkkkkksssssaaaa.practice.edacatalog.domain.entity

import jakarta.persistence.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table(value = "products")
class Product(
    id: Long,
    sellerId: Long,
    name: String,
    description: String,
    price: Long,
    stockCount: Long,
    tags: List<String>,
) {
    @PrimaryKey
    val id: Long = id

    @Column
    val sellerId: Long = sellerId

    @Column
    val name: String = name

    @Column
    val description: String = description

    @Column
    val price: Long = price

    @Column
    var stockCount: Long = stockCount

    @Column
    val tags: List<String> = tags
}