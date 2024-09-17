package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.lang.Nullable
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class AuthenticationFilter(
    private val customAuthenticationManager: CustomAuthenticationManager
): AbstractAuthenticationProcessingFilter(
    DEFAULT_ANT_PATH_REQUEST_MATCHER,
    customAuthenticationManager
) {
    companion object {
        private const val LOGIN_ID_PARAMETER = "loginId"
        private const val PASSWORD_PARAMETER = "password"
        private const val POST_ONLY_FLAG = true

        private val DEFAULT_ANT_PATH_REQUEST_MATCHER =
            AntPathRequestMatcher("/users/auth/login", "POST")
    }

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse?
    ): Authentication {
        if (POST_ONLY_FLAG && request.method != "POST") {
            throw AuthenticationServiceException("Authentication method not supported: " + request.method)
        } else {
            var username = this.obtainUsername(request)
            username = username.trim { it <= ' ' }
            val password = this.obtainPassword(request)
            val authRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(username, password)
            this.setDetails(request, authRequest)

            return authenticationManager.authenticate(authRequest)
        }
    }

    @Nullable
    protected fun obtainPassword(request: HttpServletRequest): String {
        return request.getParameter(PASSWORD_PARAMETER)
    }

    @Nullable
    protected fun obtainUsername(request: HttpServletRequest): String {
        return request.getParameter(LOGIN_ID_PARAMETER)
    }

    protected fun setDetails(
        request: HttpServletRequest?,
        authRequest: UsernamePasswordAuthenticationToken
    ) {
        authRequest.details = authenticationDetailsSource.buildDetails(request)
    }
}