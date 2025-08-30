package common.factory.models.servlet.router

import common.factory.Beans
import common.factory.models.annotations.Controller
import common.factory.models.servlet.annotations.RequestMapping
import common.factory.models.servlet.models.HandlerBean
import common.factory.models.servlet.models.HandlerFunction
import common.factory.models.servlet.models.RequestLines
import mu.KotlinLogging
import java.io.DataOutputStream
import java.util.concurrent.ConcurrentHashMap

internal object DynamicResourceRouter {
    private val log = KotlinLogging.logger {}
    private val routes: MutableMap<String, HandlerFunction> = ConcurrentHashMap<String, HandlerFunction>(128)

    fun lazyInit() {
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

        println(result)

        routes.putAll(result)
    }

    fun doRoute(
        request: RequestLines,
        dos: DataOutputStream,
    ) {
        print(request)
//        val targetRequest =
    }
}