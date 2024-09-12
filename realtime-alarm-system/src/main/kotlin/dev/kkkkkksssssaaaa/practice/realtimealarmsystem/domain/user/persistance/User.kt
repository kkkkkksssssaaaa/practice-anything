package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDate

@Entity
class User(
    name: String,
    birth: LocalDate,
): BaseEntity() {
    @Column(length = 20)
    var name: String = name
        protected set

    @Column
    var birth: LocalDate = birth
        protected set
}