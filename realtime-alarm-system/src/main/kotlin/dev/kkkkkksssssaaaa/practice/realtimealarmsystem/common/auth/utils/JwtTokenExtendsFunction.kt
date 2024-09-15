package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.utils

import com.auth0.jwt.JWTCreator
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.Claims

fun JWTCreator.Builder.withClaims(claims: Map<Claims, String>): JWTCreator.Builder {
    claims.entries.forEach { claim ->
        this.withClaim(claim.key.key, claim.value)
    }

    return this
}