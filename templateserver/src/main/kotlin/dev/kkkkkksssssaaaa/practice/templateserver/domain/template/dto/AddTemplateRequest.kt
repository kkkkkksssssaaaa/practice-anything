package dev.kkkkkksssssaaaa.practice.templateserver.domain.template.dto

data class AddTemplateRequest(
    val name: String,
    val content: String,
    val properties: List<String>
)