package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation

import java.time.LocalDate

data class MyInfoResponse(
    val id: Long,
    val personalInfo: PersonalInfo
)

data class PersonalInfo(
    val name: String,
    val birth: LocalDate
)
