package common.factory

import java.util.concurrent.ConcurrentHashMap

object Beans {
    private val values: MutableMap<String, Any> = ConcurrentHashMap(128)

    @Suppress("UNCHECKED_CAST")
    fun <T> get(name: String): T {
        return values[name] as T
    }

    fun push(name: String, value: Any) {
        this.values[name] = value
    }
}