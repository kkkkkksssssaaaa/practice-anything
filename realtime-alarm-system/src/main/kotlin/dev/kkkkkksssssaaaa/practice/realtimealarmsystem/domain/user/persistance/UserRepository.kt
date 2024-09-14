package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.persistance

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.user.dto.UserDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

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
}

@Repository
interface UserJpaRepository: JpaRepository<User, Long>

@Repository
interface UserAccountJpaRepository: JpaRepository<UserAccount, Long>