package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.userPrincipal
import org.springframework.security.core.context.SecurityContextHolder

class AuthenticatedUser {
    companion object {
        val data = SecurityContextHolder.getContext().authentication.userPrincipal
    }
}