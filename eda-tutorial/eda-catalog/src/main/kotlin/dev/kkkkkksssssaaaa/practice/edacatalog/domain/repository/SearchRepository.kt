package dev.kkkkkksssssaaaa.practice.edacatalog.domain.repository

import dev.kkkkkksssssaaaa.practice.edacatalog.domain.dto.TagsDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "searchRepository", url = "http://search-service:8080")
interface SearchRepository {
    @PostMapping("/search/tags")
    fun addTagCache(@RequestBody dto: TagsDto)

    @PutMapping("/search/tags")
    fun removeTags(@RequestBody dto: TagsDto)
}