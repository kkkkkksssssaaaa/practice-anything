package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Group(
    name: String
): BaseEntity() {
    @Column(length = 50)
    var name: String = name
        protected set
}