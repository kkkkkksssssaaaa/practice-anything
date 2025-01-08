package dev.kkkkkksssssaaaa.practice.edamember.edamember.domain.repository

import dev.kkkkkksssssaaaa.practice.edamember.edamember.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByLoginId(loginId: String): UserEntity?
}