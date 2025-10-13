package domain.user.controller

import common.factory.models.servlet.annotations.*
import common.factory.models.servlet.models.HttpStatus
import domain.user.controller.dto.CreateUserRequest
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

    @GetMapping("/{id}")
    fun getUser(
        @PathVariable id: String,
    ) {
        println("call getUser=${id}")
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    fun doCreate(
        @RequestBody request: CreateUserRequest,
    ): CreateUserRequest {
        userRepository.doRegistration(
            id = request.userId,
            name = request.name,
            password = request.password,
        )

        return request
    }
}