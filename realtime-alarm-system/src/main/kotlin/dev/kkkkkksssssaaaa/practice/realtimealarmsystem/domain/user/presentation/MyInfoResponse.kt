package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation

import java.time.LocalDate

data class MyInfoResponse(
    val id: Long,
    val personalInfo: PersonalInfo,
    val profile: MyProfile?,
)

data class PersonalInfo(
    val name: String,
    val birth: LocalDate,
    val loginId: String,
)

data class MyProfile(
    val image: String?,
    val backgroundImage: String?,
    val statusMessage: String?
)
