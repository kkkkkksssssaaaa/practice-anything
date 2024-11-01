package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.subscribe.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity.BaseEntity
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.Artist
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Subscribe(
    subscribedAt: LocalDateTime,
    expiredAt: LocalDateTime,
    user: User,
    artist: Artist,
): BaseEntity() {
    @Column(name = "subscribed_at")
    var subscribedAt: LocalDateTime = subscribedAt
        protected set

    @Column(name = "expired_at")
    var expiredAt: LocalDateTime = expiredAt
        protected set

    @JoinColumn(
        name = "user_id",
        updatable = false
    )
    @ManyToOne(fetch = FetchType.LAZY)
    var user: User = user
        protected set

    @JoinColumn(
        name = "artist_id",
        updatable = false
    )
    @ManyToOne(fetch = FetchType.LAZY)
    var artist: Artist = artist
        protected set
}