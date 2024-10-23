package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.TokenDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.Roles
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.TokenType
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.UserRepository
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TokenRefreshService(
    private val tokenService: TokenService,
    private val userRepository: UserRepository
) {
    fun doRefresh(
        token: TokenDto,
        servletResponse: HttpServletResponse
    ): TokenDto {
        val user = userRepository.findById(token.userId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user.")

        val accessToken = tokenService.createToken(user.id!!, TokenType.ACCESS, Roles.USER)

        tokenService.setRefreshToken(servletResponse, user.id)

        return accessToken
    }
}