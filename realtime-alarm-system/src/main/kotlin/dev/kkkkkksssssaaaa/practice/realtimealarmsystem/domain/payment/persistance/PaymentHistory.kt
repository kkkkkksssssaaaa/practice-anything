package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.payment.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity.BaseEntity
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.Artist
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class PaymentHistory(
    user: User,
    artist: Artist,
    price: Int,
    paidAt: LocalDateTime,
    status: PaymentStatus
): BaseEntity() {
    @JoinColumn(
        name = "user_id",
        updatable = false
    )
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User = user

    @JoinColumn(
        name = "artist_id",
        updatable = false
    )
    @ManyToOne(fetch = FetchType.LAZY)
    val artist: Artist = artist

    @Column(updatable = false)
    val price: Int = price

    @Column(updatable = false)
    val paidAt: LocalDateTime = paidAt

    @Column
    var status: PaymentStatus = status
        protected set
}