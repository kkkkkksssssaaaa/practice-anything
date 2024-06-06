package dev.kkkkkksssssaaaa.practice.templateserver.domain.template.entity

import jakarta.persistence.*

@Entity
data class Template(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,

    @Column(columnDefinition = "text")
    val content: String
)

@Entity
data class CustomProperty(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    @JoinColumn(name = "template_id")
    val template: Template,
    val name: String,
)