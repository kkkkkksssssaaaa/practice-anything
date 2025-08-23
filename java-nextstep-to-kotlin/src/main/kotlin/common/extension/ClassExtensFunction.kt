package common.extension

import common.factory.models.annotations.Component
import common.factory.models.annotations.Controller
import mu.KotlinLogging
import kotlin.reflect.KClass
import kotlin.reflect.full.allSuperclasses

private val log = KotlinLogging.logger {}

private val beanTarget: List<KClass<out Annotation>> = listOf(
    Component::class,
    Controller::class
)

fun KClass<*>.isBean(): Boolean {
    if (this.annotations.any { beanTarget.contains(it.annotationClass) }) return true

    return this.allSuperclasses.any { superType ->
        superType.annotations.any { beanTarget.contains(it.annotationClass) }
    }
}

fun <A : Annotation> KClass<*>.isBean(annotation: KClass<out Annotation>): Boolean {
    if (!beanTarget.contains(annotation)) {
        throw IllegalArgumentException("$annotation is not bean annotation.")
    }

    if (this.annotations.any { annotation == it.annotationClass }) return true

    return this.allSuperclasses.any { superType ->
        superType.annotations.any { annotation == it.annotationClass }
    }
}

fun KClass<*>.getBeanName(): String {
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