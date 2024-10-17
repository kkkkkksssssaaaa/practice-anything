package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class UserProfile(
    image: String?,
    backgroundImage: String?,
    statusMessage: String?,
    user: User
): BaseEntity() {
    @Column
    var image: String? = image
        protected set

    @Column
    var backgroundImage: String? = backgroundImage
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
    var user: User = user
        protected set
}