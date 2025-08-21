package domain.user.service

import common.factory.Component
import domain.user.repository.UserRepository
import domain.user.repository.UserRepositoryImpl

@Component
class UserRegistrationService(
    // TODO: refactor di
    private val repository: UserRepository = UserRepositoryImpl()
) {
    fun doRegistration(
        id: String,
        password: String,
        name: String
    ) {
        repository.doRegistration(
            id = id,
            password = password,
            name = name
        )
    }
}