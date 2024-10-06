package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.ArtistDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class ArtistRepository(
    private val artistJpaRepository: ArtistJpaRepository,
    private val groupJpaRepository: GroupJpaRepository
) {
    fun save(dto: ArtistDto) {
        dto.group?.let {
            groupJpaRepository.findByName(it.name).let { group ->
                artistJpaRepository.save(
                    Artist(
                        name = dto.name,
                        group = group
                    )
                )
            } ?: {
                val newGroup = Group(name = it.name)

                groupJpaRepository.save(newGroup)
                artistJpaRepository.save(
                    Artist(
                        name = dto.name,
                        group = newGroup
                    )
                )
            }
        } ?: {
            artistJpaRepository.save(
                Artist(
                    name = dto.name,
                    group = null
                )
            )
        }
    }
}

@Repository
interface ArtistJpaRepository: JpaRepository<Artist, Long>

@Repository
interface GroupJpaRepository: JpaRepository<Group, Long> {
    fun findByName(name: String): Group?
}