package domain.user.controller

import common.factory.models.annotations.Controller
import common.factory.models.servlet.annotations.GetMapping
import common.factory.models.servlet.annotations.RequestMapping
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

    @GetMapping("/create")
    fun doCreate(params: Map<String, Any>): String {
        userRepository.doRegistration(
            id = params["id"].toString(),
            name = params["name"].toString(),
            password = params["password"].toString(),
        )

        return "success"
    }
}