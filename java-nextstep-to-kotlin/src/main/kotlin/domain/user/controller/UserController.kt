package domain.user.controller

import common.factory.models.annotations.Controller
import common.factory.models.servlet.annotations.GetMapping
import common.factory.models.servlet.annotations.RequestMapping
import common.factory.models.servlet.annotations.ResponseStatus
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
    @GetMapping("/create")
    fun doCreate(params: Map<String, Any>): Map<String, Any> {
        userRepository.doRegistration(
            id = params["id"].toString(),
            name = params["name"].toString(),
            password = params["password"].toString(),
        )

        return params
    }
}