package dev.kkkkkksssssaaaa.practice.edasearch.domain.service

import blackfriday.protobuf.EdaMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class EventListener(
    private val service: SearchService
) {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @KafkaListener(topics = ["product_tags_added"])
    fun consumeAddedTags(message: ByteArray) {
        val obj = EdaMessage.ProductTags.parseFrom(message)

        log.info("[product_tags_added] consumed: $obj")

        service.saveCachedTags(
            productId = obj.productId,
            tags = obj.tagsList
        )
    }

    @KafkaListener(topics = ["product_tags_removed"])
    fun consumeRemovedTags(message: ByteArray) {
        val obj = EdaMessage.ProductTags.parseFrom(message)

        log.info("[product_tags_removed] consumed: $obj")

        service.removeCachedTag(
            productId = obj.productId,
            tags = obj.tagsList
        )
    }
}