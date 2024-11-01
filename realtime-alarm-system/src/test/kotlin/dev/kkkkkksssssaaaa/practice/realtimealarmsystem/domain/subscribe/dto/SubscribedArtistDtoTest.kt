package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.subscribe.dto

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.ArtistDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class SubscribedArtistDtoTest {
    @Test
    fun `구독 정보가 만료되었는지 확인할 수 있다`() {
        assertTrue(
            SubscribedArtistDto(
                user = UserDto(
                    id = null,
                    name = "mock_user",
                    birth = LocalDate.now(),
                    account = null,
                    profile = null
                ),
                artist = ArtistDto(
                    id = null,
                    name = "mock_artist",
                    group = null,
                    profile = null
                ),
                subscribedAt = LocalDateTime.now().minusSeconds(1L),
                expiredAt = LocalDateTime.now().minusSeconds(1L),
            ).isExpired()
        )
    }
}