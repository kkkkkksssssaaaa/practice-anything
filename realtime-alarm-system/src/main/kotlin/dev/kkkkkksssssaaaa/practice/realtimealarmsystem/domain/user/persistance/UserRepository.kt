package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserAccountDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Component
@Transactional
class UserRepository(
    private val userJpaRepository: UserJpaRepository,
    private val userAccountJpaRepository: UserAccountJpaRepository,
    private val queryFactory: JPAQueryFactory
) {
    fun save(dto: UserDto) {
        userAccountJpaRepository.save(
            UserAccount(
                user = User(
                    name = dto.name,
                    birth = dto.birth
                ),
                loginId = dto.account?.loginId!!,
                password = dto.account.password
            )
        )
    }

    fun findByLoginId(loginId: String): UserDto? {
        return UserDto(
            id = 1L,
            name = "김테스트",
            birth = LocalDate.now(),
            account = UserAccountDto(
                loginId = "test",
                password = "1234"
            )
        )
//        return queryFactory.select(
//            QUserAggregate(
//
//            )
//        )
//            .innerJoin(userAccount)
//            .on(userAccount.user.id.eq(user.id))
//            .where(userAccount.loginId.eq(loginId))
//            .fetchFirst()
//            .let {
//                UserDto(
//                    id = it.id
//                )
//            } ?: throw IllegalArgumentException()
    }

    fun findById(id: Long): UserDto? {
        return UserDto(
            id = 1L,
            name = "김테스트",
            birth = LocalDate.now(),
            account = UserAccountDto(
                loginId = "test",
                password = "1234"
            )
        )
//        return queryFactory.select(
//            QUserAggregate(
//
//            )
//        )
//            .innerJoin(userAccount)
//            .on(userAccount.user.id.eq(user.id))
//            .where(userAccount.loginId.eq(loginId))
//            .fetchFirst()
//            .let {
//                UserDto(
//                    id = it.id
//                )
//            } ?: throw IllegalArgumentException()
    }
}

@Repository
interface UserJpaRepository: JpaRepository<User, Long>

@Repository
interface UserAccountJpaRepository: JpaRepository<UserAccount, Long>