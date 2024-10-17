package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.AuthenticatedUser
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.service.UserRegistrationService
import org.springframework.http.ResponseEntity
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
    fun getMe(): ResponseEntity<MyInfoResponse> {
        val authenticatedUser = AuthenticatedUser.data

        return ResponseEntity.ok(
            MyInfoResponse(
                id = authenticatedUser.id!!,
                personalInfo = PersonalInfo(
                    name = authenticatedUser.name,
                    birth = authenticatedUser.birth
                ),
                profile = MyProfile(
                    image = authenticatedUser.profile?.image,
                    backgroundImage = authenticatedUser.profile?.backgroundImage,
                    statusMessage = authenticatedUser.profile?.statusMessage,
                )
            )
        )
    }

    @GetMapping("/me/subscribes")
    fun getMySubscribesArtist() {

    }
}