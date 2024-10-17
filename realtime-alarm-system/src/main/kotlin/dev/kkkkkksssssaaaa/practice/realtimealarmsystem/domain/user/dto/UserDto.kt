package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto

import java.time.LocalDate

class UserDto(
    val id: Long?,
    val name: String,
    val birth: LocalDate,
    val account: UserAccountDto?,
    val profile: UserProfileDto?
)

class UserAccountDto(
    val loginId: String,
    val password: String
)

class UserProfileDto(
    val image: String?,
    val backgroundImage: String?,
    val statusMessage: String?
)