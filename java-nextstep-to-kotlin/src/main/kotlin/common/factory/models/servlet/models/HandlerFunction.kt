package common.factory.models.servlet.models

import common.factory.Bean
import common.factory.models.servlet.annotations.*
import common.factory.models.servlet.annotations.RequestBody
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KFunction
import kotlin.reflect.full.valueParameters
import kotlin.reflect.jvm.jvmErasure

class HandlerFunction(
    private val pair: Pair<Bean<*>, KFunction<*>>
) {
    fun doExtract(): Pair<String, HttpMethod> {
        val findAllTargetAnnotations = this.pair.second.annotations.filter {
            mappings.contains(it.annotationClass)
        }

        if (findAllTargetAnnotations.isEmpty()) {
            throw IllegalArgumentException("Illegal handler method, name=${this.pair.second}")
        }

        if (findAllTargetAnnotations.size > 1) {
            throw IllegalArgumentException("Ambiguous handler method, ${findAllTargetAnnotations.size} handler methods!")
        }

        val targetAnnotation = findAllTargetAnnotations.first()

        return _doExtract(targetAnnotation)
    }

    fun invoke(params: Any?): Pair<KClass<*>, Any?> {
        val result = this.pair.second.call(
            this.pair.first.originInstance(),
            params
        )

        if (result == null) {
            return Unit::class to null
        }

        return this.pair.second.returnType.jvmErasure to result
    }

    fun responseStatusCode(): HttpStatus {
        val hasAnnotation = this.pair.second.annotations.find {
            it.annotationClass == ResponseStatus::class
        }

        if (hasAnnotation == null) {
            return HttpStatus.OK
        }

        return (hasAnnotation as ResponseStatus).value
    }

    fun requestBodyClassifier(): KClassifier? {
        val findResult = this.pair.second.valueParameters.find {
            it.annotations.any { ann ->
                ann.annotationClass == RequestBody::class
            }
        }

        if (findResult == null) {
            return null
        }

        return findResult.type.classifier
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