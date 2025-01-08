package dev.kkkkkksssssaaaa.practice.edamember.edamember.domain.controller

import dev.kkkkkksssssaaaa.practice.edamember.edamember.domain.dto.ModifyUserDto
import dev.kkkkkksssssaaaa.practice.edamember.edamember.domain.dto.RegistrationUserDto
import dev.kkkkkksssssaaaa.practice.edamember.edamember.domain.entity.UserEntity
import dev.kkkkkksssssaaaa.practice.edamember.edamember.domain.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(
    private val service: UserService,
) {
    @PostMapping
    fun doRegistration(
        @RequestBody dto: RegistrationUserDto
    ): ResponseEntity<UserEntity> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.registration(dto.loginId, dto.name))
    }

    @PutMapping("/{id}")
    fun doModify(
        @PathVariable("id") id: Long,
        @RequestBody dto: ModifyUserDto
    ): ResponseEntity<UserEntity> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(service.modify(id, dto.name))
    }

    @PostMapping("/{loginId}/login")
    fun doLogin(
        @PathVariable("loginId") loginId: String,
    ): ResponseEntity<UserEntity> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(service.get(loginId))
    }
}