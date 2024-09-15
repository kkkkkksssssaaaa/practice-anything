package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class TokenProperties(
    @Value("\${app.auth.issuer}") val issuer: String,
    @Value("\${app.auth.audience}") val audience: String,
    @Value("\${app.auth.access-token-secret}") val accessTokenSecret: String,
    @Value("\${app.auth.refresh-token-secret}") val refreshTokenSecret: String,
    @Value("\${app.auth.access-token-expiration}") val accessTokenExpiration: Long,
    @Value("\${app.auth.refresh-token-expiration}") val refreshTokenExpiration: Long,
)