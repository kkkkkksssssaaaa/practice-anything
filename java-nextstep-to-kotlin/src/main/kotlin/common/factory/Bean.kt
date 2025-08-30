package common.factory

import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions

open class Bean<T>(
    private val instance: T,
    private val classMetadata: KClass<*>,
) {
    private val thisType = classMetadata
    private val annotations: List<Annotation> = thisType.annotations

    fun hasAnnotation(annotationClass: KClass<out Annotation>): Boolean {
        return annotations.any { it.annotationClass == annotationClass }
    }

    fun findAnnotation(annotationClass: KClass<out Annotation>): Annotation? {
        return annotations.firstOrNull { it.annotationClass == annotationClass }
    }

    fun originInstance(): T {
        return this.instance
    }

    fun originType(): KClass<*> {
        return this.classMetadata
    }

    protected fun annotatedFunctions(
        targetAnnotations: List<KClass<out Annotation>>
    ): List<KFunction<*>> {
        return classMetadata.declaredFunctions
            .filter { function ->
                function.annotations.any {
                    targetAnnotations.contains(it.annotationClass)
                }
            }
    }
}