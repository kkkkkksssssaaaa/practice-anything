package common.factory.models.servlet.router

import common.factory.models.servlet.models.HttpStatus
import common.factory.models.servlet.models.Request

interface ResourceRouter {
    fun lazyInit()
    fun doRoute(request: Request): Pair<HttpStatus, ByteArray?>
}