package dev.kkkkkksssssaaaa.practice.edacatalog.domain.controller

import dev.kkkkkksssssaaaa.practice.edacatalog.domain.dto.DecreaseStockCountDto
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.dto.RegistrationProductDto
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.entity.Product
import dev.kkkkkksssssaaaa.practice.edacatalog.domain.service.CatalogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/catalog")
class CatalogController(
    private val service: CatalogService
) {
    @PostMapping("/products")
    fun registration(
        @RequestBody dto: RegistrationProductDto
    ): ResponseEntity<Product> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                service.registration(
                    sellerId = dto.sellerId,
                    name = dto.name,
                    description = dto.description,
                    price = dto.price,
                    stockCount = dto.stockCount,
                    tags = dto.tags
                )
            )
    }

    @DeleteMapping("/products/{productId}")
    fun delete(
        @PathVariable productId: Long
    ): ResponseEntity<Unit> {
        service.delete(productId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/products/{productId}")
    fun findById(
        @PathVariable productId: Long
    ): ResponseEntity<Product> {
        return ResponseEntity.ok(service.findById(productId))
    }

    @GetMapping("/sellers/{sellerId}/products")
    fun findAllBySellerId(
        @PathVariable sellerId: Long
    ): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(service.findAllBySellerId(sellerId))
    }

    @PostMapping("/products/{productId}/decrease")
    fun decreaseProduct(
        @PathVariable productId: Long,
        @RequestBody dto: DecreaseStockCountDto
    ): ResponseEntity<Product> {
        return ResponseEntity.ok(
            service.decreaseStockCount(productId, dto.decreaseCount)
        )
    }
}