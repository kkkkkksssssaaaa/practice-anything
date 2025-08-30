package domain.user.controller

import common.factory.models.annotations.Controller
import common.factory.models.servlet.annotations.GetMapping
import common.factory.models.servlet.annotations.RequestMapping

@Controller
@RequestMapping("/user")
class UserController {
    @GetMapping("/test")
    fun doTest() {
        println("call doTest")
    }
}