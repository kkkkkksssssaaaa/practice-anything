package dev.kkkkkksssssaaaa.practice.kafka.streams.listener

import dev.kkkkkksssssaaaa.practice.kafka.streams.utils.JsonUtils
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Grouped
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.Produced
import org.apache.kafka.streams.kstream.TimeWindows
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.time.Duration

private const val INPUT_TOPIC_NAME = "kafka-practice-checkout-complete"
private const val OUTPUT_TOPIC_NAME = "kafka-practice-checkout-product"

@Component
class StreamsListener {
    @Bean
    fun streams(builder: StreamsBuilder): KStream<String, String> {
        val inputStream = builder.stream<String, String>(INPUT_TOPIC_NAME)
        inputStream
            .map { _, v -> KeyValue(JsonUtils.getProductId(v), JsonUtils.getAmount(v)) }
            .groupByKey(Grouped.with(Serdes.Long(), Serdes.Long()))
            .windowedBy(TimeWindows.of(Duration.ofMinutes(1)))
            .reduce { value1, value2 -> value1 + value2 }
            .toStream()
            .map { key, value -> KeyValue(key.key(), value) }
            .mapValues(JsonUtils::getSendingJson)
            .selectKey { _, _ -> null }
            .to(OUTPUT_TOPIC_NAME, Produced.with(null, Serdes.String()))

        return inputStream
    }
}