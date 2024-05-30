package dev.kkkkkksssssaaaa.practice.templateserver.domain.template.dto

data class TemplateResponse(
    val id: Long,
    val name: String,
    val content: String,
    val properties: List<String>
)