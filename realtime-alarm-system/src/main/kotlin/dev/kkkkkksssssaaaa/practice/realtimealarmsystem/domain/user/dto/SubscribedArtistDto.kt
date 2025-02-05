package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.ArtistDto
import java.time.LocalDateTime

data class SubscribedArtists(
    val list: List<SubscribedArtistDto>
) {
    fun findByArtistId(id: Long): SubscribedArtistDto? {
        return list.firstOrNull { it.artist.id == id }
    }
}

data class SubscribedArtistDto(
    val id: Long? = null,
    val user: UserDto,
    val artist: ArtistDto,
    val subscribedAt: LocalDateTime,
    val expiredAt: LocalDateTime
) {
    companion object {
        fun create(user: UserDto, artist: ArtistDto) = SubscribedArtistDto(
            id = null,
            user = user,
            artist = artist,
            subscribedAt = LocalDateTime.now(),
            expiredAt = LocalDateTime.now().plusMonths(1L),
        )
    }

    fun isExpired(): Boolean {
        return expiredAt.isAfter(LocalDateTime.now())
    }

    fun refresh(): SubscribedArtistDto {
        return SubscribedArtistDto(
            id = null,
            user = this.user,
            artist = this.artist,
            subscribedAt = LocalDateTime.now(),
            expiredAt = LocalDateTime.now().plusMonths(1L),
        )
    }
}