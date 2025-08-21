package common.factory

import java.util.concurrent.ConcurrentHashMap

object Beans {
    private val values: MutableMap<String, Any> = ConcurrentHashMap(128)

    @Suppress("UNCHECKED_CAST")
    fun <T> find(name: String): T? {
        val findResult = values[name] ?: return null

        return findResult as T
    }

    fun push(name: String, value: Any) {
        this.values[name] = value
    }

    override fun toString(): String {
        return this.values.keys.joinToString { "," }
    }
}