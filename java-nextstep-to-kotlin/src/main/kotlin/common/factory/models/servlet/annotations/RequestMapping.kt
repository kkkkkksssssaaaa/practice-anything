package common.factory.models.servlet.annotations

import common.factory.models.servlet.models.HttpMethod

val mappings = listOf(
    RequestMapping::class,
    GetMapping::class,
    PostMapping::class,
    PutMapping::class,
    DeleteMapping::class,
)

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.ANNOTATION_CLASS,
)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequestMapping(
    val name: String = "",
    val method: HttpMethod = HttpMethod.GET,
)

@RequestMapping(method = HttpMethod.GET)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class GetMapping(
    val name: String = "",
)

@RequestMapping(method = HttpMethod.POST)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class PostMapping(
    val name: String = "",
)

@RequestMapping(method = HttpMethod.PUT)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class PutMapping(
    val name: String = "",
)

@RequestMapping(method = HttpMethod.DELETE)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DeleteMapping(
    val name: String = "",
)