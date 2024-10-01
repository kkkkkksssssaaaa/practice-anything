package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.utils

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.TokenDto
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

fun HttpServletRequest.refreshToken(): TokenDto {
    val cookie = this.cookies.firstOrNull {
        it.name.equals("refreshToken")
    } ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not found refresh token.")

    return TokenDto(cookie.value)
}

fun HttpServletResponse.removeRefreshToken(token: TokenDto): HttpServletResponse {
    val refreshTokenCookie = Cookie("refreshToken", token.toString())
    refreshTokenCookie.isHttpOnly = true
    refreshTokenCookie.secure = true
    refreshTokenCookie.path = "/"
    refreshTokenCookie.maxAge = 0

    this.addCookie(refreshTokenCookie)

    return this
}