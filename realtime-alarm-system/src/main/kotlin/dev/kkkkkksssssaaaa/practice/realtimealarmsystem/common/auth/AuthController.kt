package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation.UserLoginRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {
    @PostMapping("/login")
    fun doLogin(request: UserLoginRequest) {
        println("call login api")
    }
}