package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.AuthenticatedUser
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation.DoSubscribeRequest
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.service.SubscribeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/me/subscribe")
class UserSubscribeController(
    private val subscribeService: SubscribeService
) {
    @PostMapping
    fun doSubscribe(
        @RequestBody request: DoSubscribeRequest
    ): ResponseEntity<Unit> {
        val authenticatedUser = AuthenticatedUser.data

        subscribeService.doSubscribe(authenticatedUser, request)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}