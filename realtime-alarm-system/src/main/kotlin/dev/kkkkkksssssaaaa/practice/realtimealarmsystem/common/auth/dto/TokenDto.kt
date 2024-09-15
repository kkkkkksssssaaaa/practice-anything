package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto

import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.interfaces.DecodedJWT
import org.slf4j.LoggerFactory

data class TokenDto(
    private val value: String
) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val decodedToken: DecodedJWT = decodedToken()

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