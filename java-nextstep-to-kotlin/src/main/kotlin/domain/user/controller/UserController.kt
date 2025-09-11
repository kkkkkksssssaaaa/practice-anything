package domain.user.controller

import common.factory.models.servlet.annotations.*
import common.factory.models.servlet.models.HttpStatus
import domain.user.repository.UserRepository

@Controller
@RequestMapping("/user")
class UserController(
    private val userRepository: UserRepository,
) {
    @GetMapping("/test")
    fun doTest() {
        println("call doTest")
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/create/get")
    fun doCreateByGet(params: Map<String, Any>): Map<String, Any> {
        userRepository.doRegistration(
            id = params["id"].toString(),
            name = params["name"].toString(),
            password = params["password"].toString(),
        )

        return params
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    fun doCreate(
        @RequestBody params: Map<String, Any>
    ): Map<String, Any> {
        userRepository.doRegistration(
            id = params["id"].toString(),
            name = params["name"].toString(),
            password = params["password"].toString(),
        )

        return params
    }
}