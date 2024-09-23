package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class AuthenticatedUserService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun findUserByUsername(loginId: String): UserDto {
        return userRepository.findByLoginId(loginId) ?: throw UsernameNotFoundException(loginId)
    }

    @Transactional(readOnly = true)
    fun findUserById(id: Long): UserDto {
        return userRepository.findById(id)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User not found. Please re-try login."
            )
    }
}
