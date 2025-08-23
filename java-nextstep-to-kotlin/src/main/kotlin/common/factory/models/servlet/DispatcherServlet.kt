package common.factory.models.servlet

import common.factory.Beans
import common.factory.models.annotations.Controller
import java.util.concurrent.ConcurrentHashMap

object DispatcherServlet {
    private val values: MutableMap<String, Any> = ConcurrentHashMap(128)

    fun init() {
        if (this.values.isNotEmpty()) {
            throw UnsupportedOperationException("Unsupported Re-initialization of DispatcherServlet")
        }

        this.values.putAll(
            Beans.findAllByAnnotation(Controller::class)
        )

        println(this.values)
    }
}