package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto

data class Artists(
    val list: List<ArtistDto>
)

data class ArtistDto(
    val id: Long? = null,
    val name: String,
    val group: GroupDto?,
    val profile: ProfileDto?,
    val isSubscribed: Boolean = false
) {
    companion object {
        fun of(id: Long): ArtistDto {
            return ArtistDto(
                id = id,
                name = "",
                group = null,
                profile = null
            )
        }
    }

    fun setSubscribed(isSubscribed: Boolean): ArtistDto {
        return ArtistDto(
            id = this.id,
            name = this.name,
            group = this.group,
            profile = this.profile,
            isSubscribed = isSubscribed
        )
    }
}

data class GroupDto(
    val id: Long? = null,
    val name: String
)

data class ProfileDto(
    val image: String?,
    val statusMessage: String?
)