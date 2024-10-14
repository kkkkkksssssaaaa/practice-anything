package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto

data class Artists(
    val list: List<ArtistDto>
)

data class ArtistDto(
    val id: Long? = null,
    val name: String,
    val group: GroupDto?,
    val profile: ProfileDto?,
)

data class GroupDto(
    val id: Long? = null,
    val name: String
)

data class ProfileDto(
    val image: String?,
    val statusMessage: String?
)