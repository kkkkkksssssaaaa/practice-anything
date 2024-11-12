package dev.kkkkkksssssaaaa.practice.kafkatutorial.producer

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.Properties

private const val BOOTSTRAP_SERVER = "localhost:9092"
private const val TOPIC_NAME = "topic-example-3"

fun main(args: Array<String>) {
    val configs = Properties()

    configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = BOOTSTRAP_SERVER
    configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
    configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
    configs[ProducerConfig.ACKS_CONFIG] = "all"
    configs[ProducerConfig.RETRIES_CONFIG] = "100"

    val message = "First Message"
    val producer = KafkaProducer<String, String>(configs)
    val record = ProducerRecord<String, String>(TOPIC_NAME, message)

    val recordMetadata = producer.send(record).get()

    println(">>>>>>>>>>> $message, ${recordMetadata.partition()}, ${recordMetadata.offset()}")

    producer.flush()
    producer.close()
}