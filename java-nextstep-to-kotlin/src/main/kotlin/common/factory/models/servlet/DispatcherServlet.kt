package common.factory.models.servlet

import common.factory.Beans
import common.factory.models.servlet.annotations.Controller
import common.factory.models.servlet.models.HttpStatus
import common.factory.models.servlet.models.Request
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

    fun doRoute(request: Request): Pair<HttpStatus, ByteArray?> {
        if (request.header.isStaticResourceRequest()) {
            return StaticResourceRouter.doRoute(request)
        }

        return DynamicResourceRouter.doRoute(request)
    }
}