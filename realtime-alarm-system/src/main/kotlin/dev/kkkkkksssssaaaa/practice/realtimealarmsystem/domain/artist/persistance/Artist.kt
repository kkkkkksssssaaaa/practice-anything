package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Artist(
    name: String,
    status: ArtistStatus = ArtistStatus.ACTIVATED,
    group: Group? = null,
    id: Long? = null
): BaseEntity(id) {
    companion object {
        fun of(id: Long): Artist {
            return Artist(id = id, name = "", status = ArtistStatus.ACTIVATED)
        }
    }

    @Column(length = 50)
    var name: String = name
        protected set

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    var status: ArtistStatus = status
        protected set

    @JoinColumn(
        name = "group_id",
        updatable = false
    )
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST],
    )
    var group: Group? = group
        protected set
}