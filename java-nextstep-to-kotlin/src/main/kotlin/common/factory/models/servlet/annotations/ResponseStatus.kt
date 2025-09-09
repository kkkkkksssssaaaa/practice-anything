package common.factory.models.servlet.annotations

import common.factory.models.servlet.models.HttpStatus

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ResponseStatus(
    val value: HttpStatus = HttpStatus.OK,
)
