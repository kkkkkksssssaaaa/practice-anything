package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.CustomAuthentication
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.TokenDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.Roles
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.properties.Endpoints
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.server.ResponseStatusException

@Component
class AuthenticationFilter(
    private val authenticatedUserService: AuthenticatedUserService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestURI = request.requestURI
        if (requestURI.equals(Endpoints.LOGIN, ignoreCase = true)) {
            filterChain.doFilter(request, response)
            return
        }

        val token = resolveToken(request)

        if (token.isExpired()) {
            throw ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "Token was expired. Please re-try login."
            )
        }

        val authentication: Authentication =
            authenticatedUserService.findUserById(token.userId).let {
                CustomAuthentication(
                    loginId = it.account!!.loginId,
                    password = it.account.password,
                    role = Roles.USER,
                    details = it,
                    isAuthenticated = true
                )
            }

        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): TokenDto {
        val bearerToken = request.getHeader("Authorization")

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return TokenDto(bearerToken.substring(7))
        }

        throw ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
            "Please login first."
        )
    }
}