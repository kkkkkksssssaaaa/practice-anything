package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.Roles
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.server.ResponseStatusException

// Any 타입의 principal 을 UserDto 로 변환하여 반환
val Authentication.userPrincipal: UserDto
    get() = this.principal as UserDto


class CustomAuthentication(
    private val loginId: String,
    private val password: String,
    private val role: Roles,
    private val details: UserDto?,
    private var isAuthenticated: Boolean,
): Authentication {
    override fun getName(): String {
        return this.loginId
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(
            SimpleGrantedAuthority(role.name)
        )
    }

    override fun getCredentials(): Any {
        return this.password
    }

    override fun getDetails(): Any {
        TODO("Not yet implemented")
    }

    override fun getPrincipal(): Any? {
        return this.details
    }

    override fun isAuthenticated(): Boolean {
        return this.details?.let {
            true
        } ?: false
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        if (this.details == null) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }

        this.isAuthenticated = isAuthenticated
    }
}