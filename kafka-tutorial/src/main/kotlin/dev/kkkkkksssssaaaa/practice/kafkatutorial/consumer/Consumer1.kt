package dev.kkkkkksssssaaaa.practice.kafkatutorial.consumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

private const val BOOTSTRAP_SERVER = "localhost:9092"
private const val TOPIC_NAME = "topic-example-3"
private const val GROUP_NAME = "group-1"

fun main(args: Array<String>) {
    val configs = Properties()

    configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = BOOTSTRAP_SERVER
    configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
    configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
    configs[ConsumerConfig.GROUP_ID_CONFIG] = GROUP_NAME
    configs[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"

    val consumer = KafkaConsumer<String, String>(configs)
    consumer.subscribe(listOf(TOPIC_NAME))

    while (true) {
        val records = consumer.poll(Duration.ofSeconds(1))

        records.forEach { record ->
            println(">>>>>>>>>>> $record")
        }
    }
}