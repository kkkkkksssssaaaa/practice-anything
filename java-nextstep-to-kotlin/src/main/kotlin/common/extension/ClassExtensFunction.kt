package common.extension

import mu.KotlinLogging
import kotlin.reflect.KClass

private val log = KotlinLogging.logger {}

fun <A : Annotation> Class<*>.isBean(annotation: KClass<A>): Boolean {
    if (this.isAnnotationPresent(annotation.java)) return true

    return this.annotatedInterfaces.any {
        (it.type as? Class<*>)?.isAnnotationPresent(annotation.java) ?: false
    }
}

fun <A : Annotation> Class<*>.getBeanName(annotation: KClass<A>): String {
    if (this.isAnnotationPresent(annotation.java)) {
        return this.simpleName
    }

    val targetInterface = this.annotatedInterfaces.find { annotatedType ->
        (annotatedType.type as? Class<*>)?.isAnnotationPresent(annotation.java) == true
    }

    if (targetInterface == null) {
        log.error("Is not bean. target=${this.simpleName}")
        return this.simpleName
    }

    return (targetInterface.type as Class<*>).simpleName
}