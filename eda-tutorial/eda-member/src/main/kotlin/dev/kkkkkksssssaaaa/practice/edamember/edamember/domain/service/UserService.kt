package dev.kkkkkksssssaaaa.practice.edamember.edamember.domain.service

import dev.kkkkkksssssaaaa.practice.edamember.edamember.domain.entity.UserEntity
import dev.kkkkkksssssaaaa.practice.edamember.edamember.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val repository: UserRepository
) {
    @Transactional
    fun registration(loginId: String, name: String): UserEntity {
        val user = UserEntity(loginId, name)

        return repository.save(user)
    }

    @Transactional
    fun modify(id: Long, name: String): UserEntity {
        val user = repository.findById(id)
            .orElseThrow { IllegalArgumentException() }

        user.name = name

        return repository.save(user)
    }

    @Transactional(readOnly = true)
    fun get(loginId: String): UserEntity {
        return repository.findByLoginId(loginId) ?: throw IllegalArgumentException()
    }
}