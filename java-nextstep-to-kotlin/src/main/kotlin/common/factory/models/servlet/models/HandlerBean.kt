package common.factory.models.servlet.models

import common.factory.Bean
import common.factory.models.servlet.annotations.RequestMapping
import common.factory.models.servlet.annotations.mappings

class HandlerBean<T>(
    instance: Bean<T>
): Bean<T>(instance.originInstance(), instance.originType()) {
    fun methods(): List<HandlerFunction> {
        val functions = this.annotatedFunctions(mappings)

        return functions.map {
            HandlerFunction(it)
        }
    }

    fun topName(): String {
        val targetAnnotation = super.findAnnotation(RequestMapping::class) as RequestMapping

        return targetAnnotation.name
    }
}