package dev.kkkkkksssssaaaa.practice.edacatalog.domain.dto

data class RegistrationProductDto(
    val sellerId: Long,
    val name: String,
    val description: String,
    val price: Long,
    val stockCount: Long,
    val tags: List<String>,
)