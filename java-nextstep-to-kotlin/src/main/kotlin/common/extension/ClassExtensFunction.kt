package common.extension

import common.factory.annotations.Component
import common.factory.annotations.Controller
import mu.KotlinLogging
import kotlin.reflect.KClass
import kotlin.reflect.full.allSuperclasses

private val log = KotlinLogging.logger {}

private val beanTarget: List<KClass<out Annotation>> = listOf(
    Component::class,
    Controller::class
)

fun <A : Annotation> KClass<*>.isBean(): Boolean {
    if (this.annotations.any { beanTarget.contains(it.annotationClass) }) return true

    return this.allSuperclasses.any { superType ->
        superType.annotations.any { beanTarget.contains(it.annotationClass) }
    }
}

fun <A : Annotation> KClass<*>.getBeanName(): String {
    if (this.annotations.any { beanTarget.contains(it.annotationClass) }) {
        return this.simpleName!!
    }

    val targetInterface = this.supertypes
        .mapNotNull { it.classifier as? KClass<*> }
        .find { it.annotations.any { a -> beanTarget.contains(a.annotationClass) } }

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