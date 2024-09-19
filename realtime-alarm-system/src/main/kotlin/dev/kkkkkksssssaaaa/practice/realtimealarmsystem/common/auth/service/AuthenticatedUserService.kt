package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticatedUserService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun findUserByUsername(loginId: String): UserDto {
        println("custom user details service called")

        return userRepository.findByLoginId(loginId) ?: throw UsernameNotFoundException(loginId)
    }
}
