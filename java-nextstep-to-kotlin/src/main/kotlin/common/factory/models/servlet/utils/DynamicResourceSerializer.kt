package common.factory.models.servlet.utils

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

object DynamicResourceSerializer {
    fun toJson(value: Any?): String {
        return when (value) {
            null -> "null"
            is String -> "\"${value.replace("\"", "\\\"")}\""
            is Number, is Boolean -> value.toString()
            is List<*> -> value.joinToString(prefix = "[", postfix = "]") { toJson(it) }
            is Array<*> -> value.joinToString(prefix = "[", postfix = "]") { toJson(it) }
            is Map<*, *> -> value.entries.joinToString(prefix = "{", postfix = "}") {
                "\"${it.key}\" : ${toJson(it.value)}"
            }
            is Pair<*, *> -> serializePair(value)
            else -> serializeObject(value)
        }
    }

    private fun serializeObject(obj: Any): String {
        val kClass: KClass<out Any> = obj::class
        val props = kClass.memberProperties

        return props.joinToString(prefix = "{", postfix = "}") { prop ->
            val name = prop.name
            val propValue = prop.getter.call(obj)
            "\"$name\" : ${toJson(propValue)}"
        }
    }

    private fun serializePair(pair: Pair<*, *>): String {
        val clazz = pair.first
        val value = pair.second

        return "{ \"type\" : \"${(clazz as? KClass<*>)?.qualifiedName}\", " +
                "\"value\" : ${toJson(value)} }"
    }
}