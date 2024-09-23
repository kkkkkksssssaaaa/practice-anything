package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.userPrincipal
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.service.UserRegistrationService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userRegistrationService: UserRegistrationService
) {
    @PostMapping("/registration")
    fun registration(@RequestBody request: UserRegistrationRequest) {
        userRegistrationService.doRegistration(request)
    }

    @GetMapping("/me")
    fun getMe() {
        println(SecurityContextHolder.getContext().authentication.userPrincipal)
    }

    @GetMapping("/me/subscribes")
    fun getMySubscribesArtist() {

    }
}