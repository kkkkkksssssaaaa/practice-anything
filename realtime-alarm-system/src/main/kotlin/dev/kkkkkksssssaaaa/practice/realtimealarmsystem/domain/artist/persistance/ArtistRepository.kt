package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.ArtistDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.GroupDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.QArtist.artist
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.QGroup.group
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.dto.QArtistAggregate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Component
class ArtistRepository(
    private val artistJpaRepository: ArtistJpaRepository,
    private val groupJpaRepository: GroupJpaRepository,
    private val queryFactory: JPAQueryFactory,
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

    @Transactional(readOnly = true)
    fun getAllActivated(): List<ArtistDto> {
        return queryFactory.select(
            QArtistAggregate(
                artist,
                group
            )
        ).from(artist)
            .where(artist.status.eq(ArtistStatus.ACTIVATED))
            .leftJoin(group)
            .on(group.id.eq(artist.group.id))
            .fetch()
            .map { aggregate ->
                ArtistDto(
                    id = aggregate.artist.id,
                    name = aggregate.artist.name,
                    group = aggregate.group?.let { group ->
                        GroupDto(
                            id = group.id,
                            name = group.name,
                        )
                    }
                )
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