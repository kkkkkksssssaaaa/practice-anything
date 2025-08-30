package common.factory

import kotlin.reflect.KClass

open class Bean(
    private val instance: Any
) {
    private val thisType = instance::class
    private val annotations: List<Annotation> = thisType.annotations

    fun hasAnnotation(annotationClass: KClass<out Annotation>): Boolean {
        return annotations.any { it.annotationClass == annotationClass }
    }
}