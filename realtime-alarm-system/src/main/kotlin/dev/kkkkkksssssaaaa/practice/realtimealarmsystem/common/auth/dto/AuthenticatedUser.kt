package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.Roles
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthenticatedUser(
    val role: Roles,
    val loginId: String,
    val plainPassword: String
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(
            SimpleGrantedAuthority(role.name)
        )
    }

    override fun getPassword(): String {
        return this.plainPassword
    }

    override fun getUsername(): String {
        return this.loginId
    }
}