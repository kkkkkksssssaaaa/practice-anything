package common.factory.models.servlet.router

import common.factory.Beans
import common.factory.models.annotations.Component
import common.factory.models.annotations.Controller
import common.factory.models.servlet.annotations.RequestMapping
import common.factory.models.servlet.models.HandlerBean
import common.factory.models.servlet.models.HandlerFunction
import common.factory.models.servlet.models.RequestLines
import mu.KotlinLogging
import webserver.messages.Messages.notFoundBody
import java.util.concurrent.ConcurrentHashMap

@Component
internal object DynamicResourceRouter: ResourceRouter {
    private val log = KotlinLogging.logger {}
    private val routes: MutableMap<String, HandlerFunction> = ConcurrentHashMap<String, HandlerFunction>(128)

    override fun lazyInit() {
        log.info("Initialize DynamicResourceRouter")

        val controllerBeans = Beans.findAllByAnnotation(Controller::class)

        val result = controllerBeans.filter { bean ->
            bean.value.hasAnnotation(RequestMapping::class)
        }.flatMap { bean ->
            val handlerBean = HandlerBean(bean.value)

            val handlerMethods = handlerBean.methods()

            handlerMethods.map {
                val signature = it.doExtract()

                val key = "${signature.second} ${handlerBean.topName()}${signature.first}"

                key to it
            }
        }.toMap()

        routes.putAll(result)
    }

    override fun doRoute(request: RequestLines): Pair<Int, ByteArray?> {
        val findRouteFunction = routes[request.signature()]

        if (findRouteFunction == null) {
            val body = notFoundBody().toByteArray()

            return 404 to body
        }

        return try {
            val invokeResult = findRouteFunction.invoke(request.queryParameters()!!)
            TODO()
        } catch (e: Exception) {
            throw e
        }
    }
}