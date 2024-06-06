package dev.kkkkkksssssaaaa.practice.templateserver.domain.template

import dev.kkkkkksssssaaaa.practice.templateserver.domain.template.entity.CustomProperty
import dev.kkkkkksssssaaaa.practice.templateserver.domain.template.entity.Template
import org.springframework.data.jpa.repository.JpaRepository

interface TemplateRepository : JpaRepository<Template, Long>

interface CustomPropertyRepository : JpaRepository<CustomProperty, Long> {
    fun findByTemplateId(templateId: Long): List<CustomProperty>
}