package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.ArtistDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.service.FindArtistService
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.AuthenticatedUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/artists")
class ArtistController(
    private val findArtistService: FindArtistService
) {
    @GetMapping
    fun findAll(): ResponseEntity<List<ArtistDto>> {
        val user = AuthenticatedUser.data

        return ResponseEntity.ok(
            findArtistService.findAll(user).list
        )
    }
}