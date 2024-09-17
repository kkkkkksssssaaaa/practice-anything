package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.CustomAuthentication
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class CustomAuthenticationManager: AuthenticationManager {
    override fun authenticate(authentication: Authentication?): Authentication {
        println("custom authentication manager called")
        return CustomAuthentication()
    }
}