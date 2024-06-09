package dev.kkkkkksssssaaaa.practice.templateserver.common.log.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
class Log(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column
    val subject: String,

    @Column(columnDefinition = "text")
    val content: String,

    @Column
    val target: String,

    @Column
    val platform: String
)