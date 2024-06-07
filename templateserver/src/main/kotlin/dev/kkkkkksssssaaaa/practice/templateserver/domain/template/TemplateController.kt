package dev.kkkkkksssssaaaa.practice.templateserver.domain.template

import dev.kkkkkksssssaaaa.practice.templateserver.domain.template.dto.AddTemplateRequest
import dev.kkkkkksssssaaaa.practice.templateserver.domain.template.dto.TemplateResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/templates")
class TemplateController(
    private val templateService: TemplateService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addTemplate(
        @RequestBody request: AddTemplateRequest
    ): ResponseEntity<Unit> {
        templateService.save(request)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{id}")
    fun getTemplate(
        @PathVariable id: Long
    ): ResponseEntity<TemplateResponse> {
        return ResponseEntity.ok(
            templateService.find(id)
        )
    }
}

