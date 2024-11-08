package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.service

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.ArtistDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.SubscribedArtistDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.SubscribedArtists
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.UserSubscribeRepository
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.presentation.DoSubscribeRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SubscribeService(
    private val repository: UserSubscribeRepository
) {
    @Transactional
    fun doSubscribe(
        user: UserDto,
        request: DoSubscribeRequest
    ) {
        repository.save(
            SubscribedArtists(
                list = request.artists.map {
                    SubscribedArtistDto.create(
                        user = user,
                        artist = ArtistDto.of(it)
                    )
                }
            )
        )
    }

    @Transactional
    fun find(user: UserDto): SubscribedArtists {
        val subscribeHistories = repository.findAllAvailableHistoriesByUser(user)
            .map {
                SubscribedArtistDto(
                    id = it.id!!,
                    user = user,
                    artist = ArtistDto(
                        id = it.artist.id,
                        name = it.artist.name,
                        group = null,
                        profile = null
                    ),
                    expiredAt = it.expiredAt,
                    subscribedAt = it.subscribedAt
                )
            }

        return SubscribedArtists(subscribeHistories)
    }
}