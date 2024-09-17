package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.dto.AuthenticatedUser
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.enums.Roles
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthenticatedUserService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(loginId: String): UserDetails {
        println("custom user details service called")


        return userRepository.findByLoginId(loginId)?.let {
            return AuthenticatedUser(
                role = Roles.USER,
                loginId = it.account?.loginId!!,
                plainPassword = it.account.password
            )
        } ?: throw UsernameNotFoundException(loginId)
    }
}
