package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class CustomAuthentication: Authentication {
    override fun getName(): String {
        TODO("Not yet implemented")
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getCredentials(): Any {
        TODO("Not yet implemented")
    }

    override fun getDetails(): Any {
        TODO("Not yet implemented")
    }

    override fun getPrincipal(): Any {
        TODO("Not yet implemented")
    }

    override fun isAuthenticated(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        TODO("Not yet implemented")
    }

}