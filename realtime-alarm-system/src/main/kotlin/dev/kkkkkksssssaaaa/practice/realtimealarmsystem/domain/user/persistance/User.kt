package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDate

@Entity(name = "`user`")
class User(
    name: String,
    birth: LocalDate,
    id: Long? = null,
): BaseEntity(id) {
    companion object {
        fun of(id: Long): User {
            return User(id = id, name = "", birth = LocalDate.now())
        }
    }

    @Column(length = 20)
    var name: String = name
        protected set

    @Column
    var birth: LocalDate = birth
        protected set
}