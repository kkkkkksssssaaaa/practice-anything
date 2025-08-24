package common.factory.models.servlet.router

import common.factory.models.servlet.models.RequestLines
import mu.KotlinLogging
import java.io.DataOutputStream

internal object DynamicResourceRouter {
    private val log = KotlinLogging.logger {}

    fun doRoute(
        request: RequestLines,
        dos: DataOutputStream,
    ) {

    }
}