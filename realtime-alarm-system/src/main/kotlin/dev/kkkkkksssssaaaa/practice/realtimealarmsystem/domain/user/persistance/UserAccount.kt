package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity.AesConverter
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class UserAccount(
    user: User,
    loginId: String,
    password: String,
): BaseEntity() {
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var user: User = user
        protected set

    @Column(
        nullable = false,
        updatable = false,
        unique = true
    )
    var loginId: String = loginId
        protected set

    @Column(
        length = 500,
        nullable = false
    )
    @Convert(converter = AesConverter::class)
    var password: String = password
        protected set
}