package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.service

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.Artists
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.ArtistRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FindArtistService(
    private val artistRepository: ArtistRepository
) {
    @Transactional(readOnly = true)
    fun findAll(): Artists {
        return Artists(
            artistRepository.getAllActivated()
        )
    }
}