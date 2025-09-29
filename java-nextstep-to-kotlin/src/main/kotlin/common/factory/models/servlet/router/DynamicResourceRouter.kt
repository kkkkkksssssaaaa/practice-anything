package common.factory.models.servlet.router

import common.factory.Beans
import common.factory.models.annotations.Component
import common.factory.models.servlet.annotations.Controller
import common.factory.models.servlet.annotations.RequestMapping
import common.factory.models.servlet.models.HandlerBean
import common.factory.models.servlet.models.HandlerFunction
import common.factory.models.servlet.models.HttpStatus
import common.factory.models.servlet.models.Request
import common.factory.models.servlet.utils.DynamicResourceSerializer
import mu.KotlinLogging
import webserver.messages.Messages.notFoundBody
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

@Component
internal object DynamicResourceRouter: ResourceRouter {
    private val log = KotlinLogging.logger {}
    private val routes: Routes = Routes()

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

    override fun doRoute(request: Request): Pair<HttpStatus, ByteArray?> {
        val findRouteFunction = routes[request.header.signature()]

        if (findRouteFunction == null) {
            val body = notFoundBody().toByteArray()

            return HttpStatus.NOT_FOUND to body
        }

        return try {
            val responseStatus = findRouteFunction.responseStatusCode()

            val bodyType = findRouteFunction.requestBodyClassifier() as KClass<*>

            val invokeResult = findRouteFunction.invoke(
                request.body?.typedBody(bodyType)
            )

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