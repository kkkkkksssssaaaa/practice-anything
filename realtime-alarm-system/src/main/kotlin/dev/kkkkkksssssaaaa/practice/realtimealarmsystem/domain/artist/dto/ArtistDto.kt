package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto

data class ArtistDto(
    val id: Long? = null,
    val name: String,
    val group: GroupDto?
)

data class GroupDto(
    val id: Long? = null,
    val name: String
)