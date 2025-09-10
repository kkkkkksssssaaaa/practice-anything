package common.factory.models.servlet.router

import common.factory.models.servlet.models.HttpStatus
import common.factory.models.servlet.models.RequestLines

interface ResourceRouter {
    fun lazyInit()
    fun doRoute(request: RequestLines): Pair<HttpStatus, ByteArray?>
}