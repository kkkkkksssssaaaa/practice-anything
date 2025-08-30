package common.factory.models.servlet.models

import common.factory.models.servlet.annotations.*
import kotlin.reflect.KFunction

class HandlerFunction(
    private val func: KFunction<*>
) {
    fun doExtract(): Pair<String, HttpMethod> {
        val findAllTargetAnnotations = func.annotations.filter {
            mappings.contains(it.annotationClass)
        }

        if (findAllTargetAnnotations.isEmpty()) {
            throw IllegalArgumentException("Illegal handler method, name=${this.func}")
        }

        if (findAllTargetAnnotations.size > 1) {
            throw IllegalArgumentException("Ambiguous handler method, ${findAllTargetAnnotations.size} handler methods!")
        }

        val targetAnnotation = findAllTargetAnnotations.first()

        return _doExtract(targetAnnotation)
    }

    private fun _doExtract(targetAnnotation: Annotation): Pair<String, HttpMethod> {
        if (targetAnnotation is RequestMapping) {
            return targetAnnotation.name to targetAnnotation.method
        }

        if (targetAnnotation is GetMapping) {
            return targetAnnotation.name to HttpMethod.GET
        }

        if (targetAnnotation is PostMapping) {
            return targetAnnotation.name to HttpMethod.POST
        }

        if (targetAnnotation is PutMapping) {
            return targetAnnotation.name to HttpMethod.PUT
        }

        if (targetAnnotation is DeleteMapping) {
            return targetAnnotation.name to HttpMethod.DELETE
        }

        throw IllegalArgumentException("Invalid target annotation annotation name!")
    }
}