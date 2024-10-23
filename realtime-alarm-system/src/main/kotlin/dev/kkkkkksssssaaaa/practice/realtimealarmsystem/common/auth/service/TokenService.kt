package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.TokenDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.userPrincipal
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.Claims
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.Roles
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.TokenType
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.properties.TokenProperties
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService(
    val properties: TokenProperties
) {
    private val accessTokenAlgorithm = Algorithm.HMAC256(properties.accessTokenSecret)
    private val refreshTokenAlgorithm = Algorithm.HMAC256(properties.refreshTokenSecret)

    fun createToken(
        id: Long,
        type: TokenType,
        role: Roles
    ): TokenDto {
        return when (type) {
            TokenType.ACCESS -> return createAccessToken(id, role)
            TokenType.REFRESH -> return createRefreshToken(id)
        }
    }

    fun setRefreshToken(
        servletResponse: HttpServletResponse,
        userId: Long
    ): HttpServletResponse {
        val refreshToken = createToken(
            id = userId,
            type = TokenType.REFRESH,
            role = Roles.USER
        )

        val refreshTokenCookie = Cookie("refreshToken", refreshToken.toString())
        refreshTokenCookie.isHttpOnly = true
        refreshTokenCookie.secure = true
        refreshTokenCookie.path = "/"
        refreshTokenCookie.maxAge = (properties.refreshTokenExpiration / 1000).toInt()

        servletResponse.addCookie(refreshTokenCookie)

        return servletResponse
    }

    private fun createAccessToken(
        id: Long,
        role: Roles,
    ): TokenDto {
        val now = System.currentTimeMillis()
        val issueAt = Date(now)
        val expiresAt = Date(now + properties.accessTokenExpiration)

        val tokenValue = JWT.create()
            .withIssuer(properties.issuer)
            .withAudience(properties.audience)
            .withIssuedAt(issueAt)
            .withExpiresAt(expiresAt)
            .withClaim(Claims.ID.key, id)
            .withClaim(Claims.ROLES.key, listOf(role.name))
            .sign(accessTokenAlgorithm)

        return TokenDto(tokenValue)
    }

    private fun createRefreshToken(id: Long): TokenDto {
        val now = System.currentTimeMillis()
        val issueAt = Date(now)
        val expiresAt = Date(now + properties.refreshTokenExpiration)

        val tokenValue = JWT.create()
            .withIssuer(properties.issuer)
            .withAudience(properties.audience)
            .withIssuedAt(issueAt)
            .withExpiresAt(expiresAt)
            .withClaim(Claims.ID.key, id)
            .sign(refreshTokenAlgorithm)

        return TokenDto(tokenValue)
    }
}
