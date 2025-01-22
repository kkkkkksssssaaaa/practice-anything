package dev.kkkkkksssssaaaa.practice.edaorder.domain.repository

import dev.kkkkkksssssaaaa.practice.edaorder.domain.dto.DecreaseStockCountDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "catalogRepository", url = "http://catalog-service:8080")
interface CatalogRepository {
    @GetMapping("/catalog/products/{productId}")
    fun get(@PathVariable productId: Long): Map<String, Any>

    @PostMapping("/catalog/products/{productId}/decrease")
    fun decrease(
        @PathVariable productId: Long,
        @RequestBody dto: DecreaseStockCountDto
    ): Map<String, Any>
}