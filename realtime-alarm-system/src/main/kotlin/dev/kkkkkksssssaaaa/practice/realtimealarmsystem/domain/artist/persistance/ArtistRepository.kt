package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.ArtistDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Component
class ArtistRepository(
    private val artistJpaRepository: ArtistJpaRepository,
    private val groupJpaRepository: GroupJpaRepository
) {
    @Transactional
    fun save(dto: ArtistDto) {
        if (dto.group == null) {
            artistJpaRepository.findByName(
                name = dto.name,
            ) ?: {
                artistJpaRepository.save(
                    Artist(
                        name = dto.name,
                        group = null
                    )
                )
            }

        } else {
            val findGroup = groupJpaRepository.findByName(dto.group.name)

            if (findGroup == null) {
                val newGroup = groupJpaRepository.save(Group(name = dto.group.name))

                artistJpaRepository.findByName(
                    name = dto.name,
                ) ?: artistJpaRepository.save(
                    Artist(
                        name = dto.name,
                        group = newGroup
                    )
                )
            } else {
                artistJpaRepository.findByName(
                    name = dto.name,
                ) ?: artistJpaRepository.save(
                    Artist(
                        name = dto.name,
                        group = findGroup
                    )
                )
            }
        }
    }
}

@Repository
interface ArtistJpaRepository: JpaRepository<Artist, Long> {
    fun findByName(name: String): Artist?
}

@Repository
interface GroupJpaRepository: JpaRepository<Group, Long> {
    fun findByName(name: String): Group?
}