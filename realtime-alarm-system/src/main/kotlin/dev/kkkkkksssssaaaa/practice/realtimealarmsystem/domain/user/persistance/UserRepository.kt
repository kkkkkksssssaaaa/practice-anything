package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserAccountDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.QUser.user
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance.QUserAccount.userAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

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
        return queryFactory.select(
            QUserAggregate(
                user,
                userAccount
            )
        ).from(userAccount)
            .where(userAccount.loginId.eq(loginId))
            .innerJoin(user)
            .on(user.id.eq(userAccount.user.id))
            .fetchFirst()?.let {
                UserDto(
                    id = it.user.id,
                    name = it.user.name,
                    birth = it.user.birth,
                    account = UserAccountDto(
                        loginId = it.account.loginId,
                        password = it.account.password
                    )
                )
            } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user.")
    }

    fun findById(id: Long): UserDto? {
        return queryFactory.select(
            QUserAggregate(
                user,
                userAccount
            )
        ).from(userAccount)
            .where(userAccount.user.id.eq(id))
            .innerJoin(user)
            .on(user.id.eq(userAccount.user.id))
            .fetchFirst()?.let {
                UserDto(
                    id = it.user.id,
                    name = it.user.name,
                    birth = it.user.birth,
                    account = UserAccountDto(
                        loginId = it.account.loginId,
                        password = it.account.password
                    )
                )
            } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user.")
    }
}

@Repository
interface UserJpaRepository: JpaRepository<User, Long>

@Repository
interface UserAccountJpaRepository: JpaRepository<UserAccount, Long>