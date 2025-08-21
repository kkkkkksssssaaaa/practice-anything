package common.extension

import mu.KotlinLogging
import kotlin.reflect.KClass
import kotlin.reflect.full.allSuperclasses

private val log = KotlinLogging.logger {}

fun <A : Annotation> KClass<*>.isBean(annotation: KClass<A>): Boolean {
    if (this.annotations.any { it.annotationClass == annotation }) return true

    return this.allSuperclasses.any { superType ->
        superType.annotations.any { it.annotationClass == annotation }
    }
}

fun <A : Annotation> KClass<*>.getBeanName(annotation: KClass<A>): String {
    if (this.annotations.any { it.annotationClass == annotation }) {
        return this.simpleName!!
    }

    val targetInterface = this.supertypes
        .mapNotNull { it.classifier as? KClass<*> }
        .find { it.annotations.any { a -> a.annotationClass == annotation } }

    if (targetInterface == null) {
        log.error("Is not bean. target=${this.simpleName}")
        return this.simpleName!!
    }

    return targetInterface.simpleName!!
}

fun KClass<*>.isInterface(): Boolean {
    return this.java.isInterface
}

fun KClass<*>.isEnum(): Boolean {
    return this.java.isEnum
}