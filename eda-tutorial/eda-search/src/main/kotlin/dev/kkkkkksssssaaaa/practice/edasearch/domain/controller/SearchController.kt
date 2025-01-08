package dev.kkkkkksssssaaaa.practice.edasearch.domain.controller

import dev.kkkkkksssssaaaa.practice.edasearch.domain.dto.TagDto
import dev.kkkkkksssssaaaa.practice.edasearch.domain.service.SearchService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search")
class SearchController(
    private val service: SearchService
) {
    @PostMapping("/tags")
    fun save(
        @RequestBody dto: TagDto
    ) {
        service.saveCachedTags(
            productId = dto.productId,
            tags = dto.tags
        )
    }

    @PutMapping("/tags")
    fun delete(
        @RequestBody dto: TagDto
    ) {
        service.removeCachedTag(
            productId = dto.productId,
            tags = dto.tags
        )
    }

    @GetMapping
    fun doSearch(tag: String): ResponseEntity<List<Long>> {
        return ResponseEntity.ok(
            service.getProductId(tag)
        )
    }
}