package common.factory.models.servlet.models

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class RequestBody(
    private val content: String,
) {
    companion object {
        fun parseJsonToMap(body: String): Map<String, Any> {
            val trimmed = body.trim().removePrefix("{").removeSuffix("}")

            if (trimmed.isEmpty()) return emptyMap()

            return trimmed.split(",").mapNotNull {
                val parts = it.split(":", limit = 2)

                if (parts.size != 2) {
                    return@mapNotNull null
                }

                val key = parts[0].trim().removeSurrounding("\"")
                val valueRaw = parts[1].trim().removeSurrounding("\"")

                val value: Any = valueRaw.toIntOrNull()
                    ?: valueRaw.toDoubleOrNull()
                    ?: valueRaw.toBooleanStrictOrNull()
                    ?: valueRaw

                key to value
            }.toMap()
        }

        fun parseFormToMap(body: String): Map<String, Any> {
            return body.split("&").associate {
                val (k, v) = it.split("=", limit = 2)

                k to v
            }
        }
    }

    fun <T : Any> typedBody(
        clazz: KClass<T>,
        params: Map<String, Any?>? = null,
        isFirst: Boolean = true,
    ): T {
        val thisMap = if (isFirst) {
            parseJsonToMap(this.content)
        } else {
            params
        }

        val targetConstructor = clazz.primaryConstructor
            ?: throw IllegalArgumentException("No primary constructor for ${clazz.simpleName}")

        val args = targetConstructor.parameters.associateWith { parameter ->
            val value = thisMap?.get(parameter.name)

            when (parameter.type.classifier) {
                Int::class -> (value as? String)?.toInt() ?: (value as? Number)?.toInt()
                Long::class -> (value as? String)?.toLong() ?: (value as? Number)?.toLong()
                Double::class -> (value as? String)?.toDouble() ?: (value as? Number)?.toDouble()
                Boolean::class -> (value as? String)?.toBoolean() ?: (value as? Boolean)
                String::class -> value?.toString()
                is KClass<*> -> {
                    if (value is Map<*, *>) {
                        @Suppress("UNCHECKED_CAST")
                        typedBody(
                            clazz = parameter.type.classifier as KClass<Any>,
                            params = value as Map<String, Any?>,
                            isFirst = false,
                        )
                    } else value
                }
                else -> value
            }
        }

        return targetConstructor.callBy(args)
    }
}