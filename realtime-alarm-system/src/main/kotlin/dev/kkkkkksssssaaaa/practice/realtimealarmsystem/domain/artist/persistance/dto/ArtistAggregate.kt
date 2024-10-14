package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.dto

import com.querydsl.core.annotations.QueryProjection
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.Artist
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.ArtistProfile
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.Group

data class ArtistAggregate @QueryProjection constructor(
    val artist: Artist,
    val group: Group? = null,
    val profile: ArtistProfile? = null,
)