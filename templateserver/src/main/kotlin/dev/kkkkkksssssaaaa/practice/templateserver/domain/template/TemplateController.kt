package dev.kkkkkksssssaaaa.practice.templateserver.domain.template

import dev.kkkkkksssssaaaa.practice.templateserver.domain.email.EmailService
import dev.kkkkkksssssaaaa.practice.templateserver.domain.email.SendMailRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TemplateController(
    private val emailService: EmailService,
    private val templateRepository: TemplateRepository,
    private val customPropertyRepository: CustomPropertyRepository
) {
    @PostMapping("/templates")
    fun addTemplate(@RequestBody template: Template): ResponseEntity<Template> {
        return ResponseEntity.ok(templateRepository.save(template))
    }

    @PostMapping("/templates/{templateId}/properties")
    fun addCustomProperty(
        @PathVariable templateId: Long,
        @RequestBody property: CustomProperty
    ): ResponseEntity<CustomProperty> {
        val newProperty = customPropertyRepository.save(property.copy(templateId = templateId))
        return ResponseEntity.ok(newProperty)
    }

    @GetMapping("/templates/{templateId}")
    fun getTemplate(@PathVariable templateId: Long): ResponseEntity<Any> {
        val template = templateRepository.findById(templateId).orElseThrow { IllegalArgumentException("Template not found") }
        val properties = customPropertyRepository.findByTemplateId(templateId)
        return ResponseEntity.ok(mapOf("template" to template, "properties" to properties))
    }

    @PostMapping("/send_mail")
    fun sendMail(@RequestBody request: SendMailRequest): ResponseEntity<String> {
        emailService.sendEmail(request.templateId, request.recipient, request.properties)
        return ResponseEntity.ok("Email sent successfully")
    }
}

