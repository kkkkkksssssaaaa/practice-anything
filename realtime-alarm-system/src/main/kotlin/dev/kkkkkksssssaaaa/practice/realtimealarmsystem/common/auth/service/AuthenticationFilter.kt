package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service

import com.fasterxml.jackson.databind.ObjectMapper
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.userPrincipal
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.Roles
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.TokenType
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation.UserLoginRequest
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation.UserTokenResponse
import jakarta.servlet.FilterChain
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.lang.Nullable
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class AuthenticationFilter(
    customAuthenticationManager: CustomAuthenticationManager,
    private val objectMapper: ObjectMapper,
    private val tokenService: TokenService
): AbstractAuthenticationProcessingFilter(
    DEFAULT_ANT_PATH_REQUEST_MATCHER,
    customAuthenticationManager
) {
    companion object {
        private const val POST_ONLY_FLAG = true

        private val DEFAULT_ANT_PATH_REQUEST_MATCHER =
            AntPathRequestMatcher("/auth/login", "POST")
    }

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse?
    ): Authentication {
        if (POST_ONLY_FLAG && request.method != "POST") {
            throw AuthenticationServiceException("Authentication method not supported: " + request.method)
        }

        val readBody = objectMapper.readValue(
            request.reader, UserLoginRequest::class.java
        )

        val username = readBody.loginId
        val password = readBody.password
        val authRequest =
            UsernamePasswordAuthenticationToken.unauthenticated(username, password)
        this.setDetails(request, authRequest)

        return authenticationManager.authenticate(authRequest)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authentication: Authentication
    ) {
        println("authentication success")

        setRefreshToken(response, authentication)
        writeResponse(response, authentication)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        println("authentication failed")

        logger.trace("Failed to process authentication request", failed)
        logger.trace("Cleared SecurityContextHolder")
        logger.trace("Handling authentication failure")
        failureHandler.onAuthenticationFailure(request, response, failed)
    }

    @Nullable
    protected fun obtainUsername(request: HttpServletRequest): String {
        throw UnsupportedOperationException()
    }

    @Nullable
    protected fun obtainPassword(request: HttpServletRequest): String {
        throw UnsupportedOperationException()
    }

    protected fun setDetails(
        request: HttpServletRequest?,
        authRequest: UsernamePasswordAuthenticationToken
    ) {
        authRequest.details = authenticationDetailsSource.buildDetails(request)
    }

    private fun setRefreshToken(
        servletResponse: HttpServletResponse,
        authentication: Authentication
    ): HttpServletResponse {
        val refreshToken = tokenService.createToken(
            id = authentication.userPrincipal.id!!,
            type = TokenType.REFRESH,
            role = Roles.USER
        )

        val refreshTokenCookie = Cookie("refreshToken", refreshToken.toString())
        refreshTokenCookie.isHttpOnly = true
        refreshTokenCookie.secure = false
        refreshTokenCookie.path = "/"
        refreshTokenCookie.maxAge = 7 * 24 * 60 * 60 // 7 days

        servletResponse.addCookie(refreshTokenCookie)

        return servletResponse
    }

    private fun writeResponse(
        servletResponse: HttpServletResponse,
        authentication: Authentication
    ) {
        val accessToken = tokenService.createToken(
            id = authentication.userPrincipal.id!!,
            type = TokenType.ACCESS,
            role = Roles.USER
        )

        servletResponse.contentType = "application/json"
        servletResponse.characterEncoding = "UTF-8"

        servletResponse.writer.write(
            objectMapper.writeValueAsString(
                UserTokenResponse(accessToken.toString())
            )
        )
    }
}