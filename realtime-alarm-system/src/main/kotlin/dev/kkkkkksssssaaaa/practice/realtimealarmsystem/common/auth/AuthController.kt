package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.utils.refreshToken
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.utils.removeRefreshToken
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
class AuthController {
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
}