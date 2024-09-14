package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto

import java.time.LocalDate

class UserDto(
    val id: Long?,
    val name: String,
    val birth: LocalDate,
    val account: UserAccountDto?
) {
}

class UserAccountDto(
    val loginId: String,
    val password: String
)