package common.factory.models.servlet.router

import common.factory.Beans
import common.factory.models.annotations.Component
import common.factory.models.annotations.Controller
import common.factory.models.servlet.annotations.RequestMapping
import common.factory.models.servlet.models.HandlerBean
import common.factory.models.servlet.models.HandlerFunction
import common.factory.models.servlet.models.HttpStatus
import common.factory.models.servlet.models.RequestLines
import common.factory.models.servlet.utils.DynamicResourceSerializer
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

    override fun doRoute(request: RequestLines): Pair<HttpStatus, ByteArray?> {
        val findRouteFunction = routes[request.signature()]

        if (findRouteFunction == null) {
            val body = notFoundBody().toByteArray()

            return HttpStatus.NOT_FOUND to body
        }

        return try {
            val responseStatus = findRouteFunction.responseStatusCode()

            val invokeResult = findRouteFunction.invoke(request.queryParameters()!!)

            if (invokeResult.second == null) {
                return responseStatus to null
            }

            val resultToJson = DynamicResourceSerializer.toJson(invokeResult.second)

            return responseStatus to resultToJson.toByteArray()
        } catch (e: Exception) {
            throw e
        }
    }
}