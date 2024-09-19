package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.CustomAuthentication
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.Roles
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CustomAuthenticationManager(
    private val authenticatedUserService: AuthenticatedUserService
): AuthenticationManager {
    override fun authenticate(authentication: Authentication): Authentication {
        println("custom authentication manager called")

        val userDto = authenticatedUserService.findUserByUsername(authentication.name)

        if (userDto.account!!.password != authentication.credentials.toString()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }

        return CustomAuthentication(
            loginId = userDto.account.loginId,
            password = userDto.account.password,
            role = Roles.USER,
            details = userDto,
            isAuthenticated = true
        )
    }
}