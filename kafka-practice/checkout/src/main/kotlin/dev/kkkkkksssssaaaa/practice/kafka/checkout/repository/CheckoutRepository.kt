package dev.kkkkkksssssaaaa.practice.kafka.checkout.repository

import dev.kkkkkksssssaaaa.practice.kafka.checkout.entity.CheckoutEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CheckoutRepository: JpaRepository<CheckoutEntity, Long>