package dev.kkkkkksssssaaaa.practice.edasearch.domain.controller

import dev.kkkkkksssssaaaa.practice.edasearch.domain.service.SearchService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search")
class SearchController(
    private val service: SearchService
) {
    @GetMapping
    fun doSearch(tag: String): ResponseEntity<List<Long>> {
        return ResponseEntity.ok(
            service.getProductId(tag)
        )
    }
}