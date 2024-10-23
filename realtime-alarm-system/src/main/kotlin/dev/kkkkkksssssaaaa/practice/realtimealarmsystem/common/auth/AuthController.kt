package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service.TokenRefreshService
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.utils.refreshToken
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.utils.removeRefreshToken
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation.UserTokenResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val tokenRefreshService: TokenRefreshService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun doLogout(
        servletRequest: HttpServletRequest,
        servletResponse: HttpServletResponse,
    ): ResponseEntity<Unit> {
        servletResponse.removeRefreshToken(
            servletRequest.refreshToken()
        )

        log.info("logout complete")

        return ResponseEntity.noContent().build()
    }

    @PostMapping("/token/refresh")
    fun doTokenRefresh(
        servletRequest: HttpServletRequest,
        servletResponse: HttpServletResponse,
    ): ResponseEntity<UserTokenResponse> {
        return ResponseEntity.ok(
            UserTokenResponse(
                tokenRefreshService.doRefresh(
                    servletRequest.refreshToken(),
                    servletResponse
                ).toString()
            )
        )
    }
}