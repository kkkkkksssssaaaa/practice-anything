package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.service

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserAccountDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.UserRepository
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation.UserRegistrationRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserRegistrationService(
    private val repository: UserRepository
) {
    @Transactional
    fun doRegistration(request: UserRegistrationRequest) {
        val toDto = UserDto(
            id = null,
            name = request.personalInfo.name,
            birth = request.personalInfo.birth,
            account = UserAccountDto(
                loginId = request.loginId,
                password = request.password
            ),
            profile = null
        )

        repository.save(toDto)
    }
}