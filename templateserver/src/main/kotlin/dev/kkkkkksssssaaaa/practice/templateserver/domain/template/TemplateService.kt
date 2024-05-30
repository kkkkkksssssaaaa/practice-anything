package dev.kkkkkksssssaaaa.practice.templateserver.domain.template

import dev.kkkkkksssssaaaa.practice.templateserver.domain.template.dto.AddTemplateRequest
import dev.kkkkkksssssaaaa.practice.templateserver.domain.template.dto.TemplateResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TemplateService(
    private val templateRepository: TemplateRepository,
    private val customPropertyRepository: CustomPropertyRepository
) {
    @Transactional
    fun save(request: AddTemplateRequest) {
        val entity = templateRepository.saveAndFlush(
            Template(
                name = request.name,
                content = request.content
            )
        )

        customPropertyRepository.saveAll(
            request.properties.map {
                CustomProperty(
                    templateId = entity.id,
                    name = it,
                )
            }
        )
    }

    @Transactional(readOnly = true)
    fun find(id: Long): TemplateResponse {
        val entity = templateRepository.findById(id)
            .orElseThrow {
                IllegalArgumentException()
            }

        val properties = customPropertyRepository.findByTemplateId(entity.id)

        return TemplateResponse(
            id = entity.id,
            name = entity.name,
            content = entity.content,
            properties = properties.map { it.name }
        )
    }
}