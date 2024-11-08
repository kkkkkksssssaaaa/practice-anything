package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.Artist
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.SubscribedArtists
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.QUserSubscribeHistory.userSubscribeHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
@Transactional
class UserSubscribeRepository(
    private val userSubscribeHistoryJpaRepository: UserSubscribeHistoryJpaRepository,
    private val queryFactory: JPAQueryFactory
) {
    fun save(dto: SubscribedArtists) {
        userSubscribeHistoryJpaRepository.saveAll(
            dto.list.map {
                UserSubscribeHistory(
                    user = User.of(it.user.id!!),
                    artist = Artist.of(it.artist.id!!),
                    subscribedAt = LocalDateTime.now(),
                    expiredAt = LocalDateTime.now().plusMonths(1)
                )
            }
        )
    }

    fun findAllAvailableHistoriesByUser(dto: UserDto): List<UserSubscribeHistory> {
        return queryFactory.selectFrom(userSubscribeHistory)
            .where(
                userSubscribeHistory.user.id.eq(dto.id)
                    .and(userSubscribeHistory.expiredAt.after(LocalDateTime.now()))
            ).fetch()
    }
}

@Repository
interface UserSubscribeHistoryJpaRepository : JpaRepository<UserSubscribeHistory, Long>