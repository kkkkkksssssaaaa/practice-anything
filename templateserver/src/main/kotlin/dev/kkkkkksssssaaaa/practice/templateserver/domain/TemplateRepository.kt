package dev.kkkkkksssssaaaa.practice.templateserver.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TemplateRepository : JpaRepository<Template, Long>

interface CustomPropertyRepository : JpaRepository<CustomProperty, Long> {
    fun findByTemplateId(templateId: Long): List<CustomProperty>
}