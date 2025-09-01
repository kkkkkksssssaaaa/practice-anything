package common.factory.models.servlet

import common.factory.Beans
import common.factory.models.annotations.Controller
import common.factory.models.servlet.models.RequestLines
import common.factory.models.servlet.router.DynamicResourceRouter
import common.factory.models.servlet.router.StaticResourceRouter
import java.util.concurrent.ConcurrentHashMap

object DispatcherServlet {
    private val values: MutableMap<String, Any> = ConcurrentHashMap(128)

    fun lazyInit() {
        if (this.values.isNotEmpty()) {
            throw UnsupportedOperationException("Unsupported Re-initialization of DispatcherServlet")
        }

        this.values.putAll(
            Beans.findAllByAnnotation(Controller::class)
        )

        DynamicResourceRouter.lazyInit()
    }

    fun doRoute(request: RequestLines): Pair<Int, ByteArray?> {
        if (request.isStaticResourceRequest()) {
            return StaticResourceRouter.doRoute(request)
        }

        return DynamicResourceRouter.doRoute(request)
    }
}