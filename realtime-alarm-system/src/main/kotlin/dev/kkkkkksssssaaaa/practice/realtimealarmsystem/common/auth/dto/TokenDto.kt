package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto

import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.interfaces.DecodedJWT
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.Claims
import org.slf4j.LoggerFactory
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class TokenDto(
    private val value: String,
) {
    private val decodedToken: DecodedJWT = decodedToken()
    private val log = LoggerFactory.getLogger(javaClass)

    val userId: Long = decodedToken.claims[Claims.ID.key].toString().toLong()
    private val expiresAt: LocalDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochSecond(
            decodedToken.claims["exp"]!!.asLong(),
        ),
        ZoneId.of("Asia/Seoul")
    )

    fun isExpired(): Boolean {
        return expiresAt < LocalDateTime.now()
    }

    override fun toString(): String {
        return value
    }

    private fun decodedToken(): DecodedJWT {
        try {
            return JWT.decode(value)
        } catch (ex: JWTDecodeException) {
            log.error(ex.message, ex)

            throw IllegalArgumentException(ex.message)
        }
    }
}