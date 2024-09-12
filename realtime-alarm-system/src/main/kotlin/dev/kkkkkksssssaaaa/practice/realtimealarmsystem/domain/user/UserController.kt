package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {
    @PostMapping
    fun registration() {

    }

    @GetMapping("/me")
    fun getMe() {

    }

    @GetMapping("/me/subscribes")
    fun getMySubscribesArtist() {

    }
}