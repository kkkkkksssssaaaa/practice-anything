package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation

import java.time.LocalDate

data class UserRegistrationRequest(
    val loginId: String,
    val password: String,
    val personalInfo: UserPersonalInfo
)

data class UserPersonalInfo(
    val name: String,
    val birth: LocalDate
)