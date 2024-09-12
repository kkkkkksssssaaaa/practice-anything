package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity.BaseEntity
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.Artist
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class UserSubscribeHistory(
    user: User,
    artist: Artist,
    startedAt: LocalDateTime,
    expiredAt: LocalDateTime,
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
    val startedAt: LocalDateTime = startedAt

    @Column
    var expiredAt: LocalDateTime = expiredAt
        protected set
}