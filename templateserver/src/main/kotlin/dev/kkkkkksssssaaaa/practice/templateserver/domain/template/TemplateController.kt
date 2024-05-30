package dev.kkkkkksssssaaaa.practice.templateserver.domain.template

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/templates")
class TemplateController(
    private val templateRepository: TemplateRepository,
    private val customPropertyRepository: CustomPropertyRepository
) {
    @PostMapping
    fun addTemplate(
        @RequestBody template: Template
    ): ResponseEntity<Template> {
        return ResponseEntity.ok(
            templateRepository.save(template)
        )
    }

    @PostMapping("/{templateId}/properties")
    fun addCustomProperty(
        @PathVariable templateId: Long,
        @RequestBody property: CustomProperty
    ): ResponseEntity<CustomProperty> {
        val newProperty = customPropertyRepository.save(
            property.copy(templateId = templateId)
        )

        return ResponseEntity.ok(newProperty)
    }

    @GetMapping("/{templateId}")
    fun getTemplate(
        @PathVariable templateId: Long
    ): ResponseEntity<Any> {
        val template = templateRepository.findById(templateId)
            .orElseThrow { IllegalArgumentException("Template not found") }

        val properties = customPropertyRepository.findByTemplateId(templateId)

        return ResponseEntity.ok(
            mapOf(
                "template" to template,
                "properties" to properties
            )
        )
    }
}

