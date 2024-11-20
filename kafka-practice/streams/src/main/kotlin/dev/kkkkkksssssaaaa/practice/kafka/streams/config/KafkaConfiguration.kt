package dev.kkkkkksssssaaaa.practice.kafka.streams.config

import org.apache.kafka.streams.StreamsConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration
import org.springframework.kafka.config.KafkaStreamsConfiguration

@Configuration
@EnableKafka
@EnableKafkaStreams
class KafkaConfiguration {
    private val BOOTSTRAP_SERVERS = "localhost:9092"

    @Bean(name = [KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME])
    fun kafkaStreamsConfig(kafkaStreamsDefaultConfiguration: KafkaStreamsDefaultConfiguration): KafkaStreamsConfiguration {
        val configProps = mapOf(
            StreamsConfig.APPLICATION_ID_CONFIG to "kafka-streams",
            StreamsConfig.BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVERS,
            StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG to String::class.java,
            StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG to String::class.java,
            StreamsConfig.COMMIT_INTERVAL_MS_CONFIG to 1000L,
        )

        return KafkaStreamsConfiguration(configProps)
    }
}