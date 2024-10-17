package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance

import com.querydsl.core.annotations.QueryProjection

data class UserAggregate @QueryProjection constructor(
    val user: User,
    val account: UserAccount,
    val profile: UserProfile?
)