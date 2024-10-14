package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class ArtistProfile(
    image: String?,
    statusMessage: String?,
    artist: Artist
): BaseEntity() {
    @Column
    var image: String? = image
        protected set

    @Column
    var statusMessage: String? = statusMessage
        protected set

    @JoinColumn(
        name = "artist_id",
        updatable = false
    )
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST],
    )
    var artist: Artist = artist
        protected set
}