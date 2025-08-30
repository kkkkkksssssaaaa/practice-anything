package common.factory

import common.extension.isBean
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

object Beans {
    private val values: MutableMap<String, Bean<*>> = ConcurrentHashMap(128)

    @Suppress("UNCHECKED_CAST")
    fun <T> find(name: String): T? {
        val findResult = values[name] ?: return null

        return findResult as T
    }

    fun push(name: String, value: Bean<*>) {
        this.values[name] = value
    }

    fun findAllByAnnotation(target: KClass<out Annotation>): Map<String, Bean<*>> {
        return values.filter { entry ->
            entry.value.originType().isBean<Annotation>(target)
        }
    }

    override fun toString(): String {
        return this.values.keys.joinToString { "," }
    }
}