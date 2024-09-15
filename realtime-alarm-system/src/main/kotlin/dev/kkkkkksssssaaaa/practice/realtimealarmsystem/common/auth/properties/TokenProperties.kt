package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app.auth")
data class TokenProperties(
    val issuer: String,
    val audience: String,
    val accessTokenSecret: String,
    val refreshTokenSecret: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long,
)