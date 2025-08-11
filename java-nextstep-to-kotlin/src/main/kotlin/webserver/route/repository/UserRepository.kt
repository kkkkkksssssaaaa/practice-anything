package webserver.route.repository

import model.User
import webserver.route.repository.UserEntities.items

interface UserRepository {
    fun doRegistration(id: String, password: String, name: String)
}

internal class UserRepositoryImpl : UserRepository {
    override fun doRegistration(id: String, password: String, name: String) {
        val find = items[id]

        if (find != null) {
            throw IllegalStateException("User already exists")
        }

        items[id] = User(
            id = id,
            password = password,
            name = name
        )
    }
}

object UserEntities {
    val items: MutableMap<String, User> = mutableMapOf()
}