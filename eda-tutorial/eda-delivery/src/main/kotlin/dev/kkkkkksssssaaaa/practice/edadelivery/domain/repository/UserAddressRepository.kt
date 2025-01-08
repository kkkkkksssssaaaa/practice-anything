package dev.kkkkkksssssaaaa.practice.edadelivery.domain.repository

import dev.kkkkkksssssaaaa.practice.edadelivery.domain.entity.UserAddress
import org.springframework.data.jpa.repository.JpaRepository

interface UserAddressRepository: JpaRepository<UserAddress, Long> {
    fun findAllByUserId(userId: Long): List<UserAddress>
}