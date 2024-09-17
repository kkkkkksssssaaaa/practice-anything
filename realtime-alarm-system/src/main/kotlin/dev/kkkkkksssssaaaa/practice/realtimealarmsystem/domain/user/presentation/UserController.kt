package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.service.UserRegistrationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userRegistrationService: UserRegistrationService
) {
    @PostMapping("/auth/login")
    fun doLogin(@RequestBody request: UserLoginRequest) {

    }

    @PostMapping("/registration")
    fun registration(@RequestBody request: UserRegistrationRequest) {
        userRegistrationService.doRegistration(request)
    }

    @GetMapping("/me")
    fun getMe() {

    }

    @GetMapping("/me/subscribes")
    fun getMySubscribesArtist() {

    }
}