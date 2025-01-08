package dev.kkkkkksssssaaaa.practice.edasearch.domain.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val stringRedisTemplate: StringRedisTemplate
) {
    fun saveCachedTags(
        productId: Long,
        tags: List<String>,
    ) {
        val cache = stringRedisTemplate.opsForSet()

        tags.forEach {
            cache.add(it, productId.toString())
        }
    }

    fun removeCachedTag(
        productId: Long,
        tags: List<String>,
    ) {
        val cache = stringRedisTemplate.opsForSet()

        tags.forEach {
            cache.remove(it, productId.toString())
        }
    }

    fun getProductId(tag: String): List<Long> {
        val cache = stringRedisTemplate.opsForSet()

        val members = cache.members(tag)

        if (members != null) {
            return members.map(String::toLong)
        }

        return emptyList()
    }
}