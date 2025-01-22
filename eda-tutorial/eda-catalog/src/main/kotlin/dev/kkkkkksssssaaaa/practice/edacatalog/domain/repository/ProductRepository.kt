package dev.kkkkkksssssaaaa.practice.edacatalog.domain.repository

import dev.kkkkkksssssaaaa.practice.edacatalog.domain.entity.Product
import org.springframework.data.cassandra.repository.CassandraRepository

interface ProductRepository: CassandraRepository<Product, Long> {
}