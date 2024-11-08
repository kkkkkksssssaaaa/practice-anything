package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.service

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.Artists
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.ArtistRepository
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.service.SubscribeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FindArtistService(
    private val artistRepository: ArtistRepository,
    private val subscribeService: SubscribeService
) {
    @Transactional(readOnly = true)
    fun findAll(user: UserDto): Artists {
        val allArtists = artistRepository.getAllActivated()
        val subscribedArtists = subscribeService.find(user)

        val result = allArtists.map {
            val subscribeHistory = subscribedArtists.findByArtistId(it.id!!)

            it.setSubscribed(
                subscribeHistory?.isExpired() ?: false,
            )
        }

        return Artists(result)
    }
}