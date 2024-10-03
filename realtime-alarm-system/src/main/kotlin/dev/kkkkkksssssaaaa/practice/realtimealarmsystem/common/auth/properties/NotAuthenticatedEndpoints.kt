package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.properties

class NotAuthenticatedEndpoints {
    companion object {
        const val LOGIN = "/auth/login"
        const val REGISTRATION = "/users/registration"

        private val ENDPOINTS = listOf(
            LOGIN,
            REGISTRATION
        )

        fun toList(): List<String> {
            return ENDPOINTS
        }

        fun contains(value: String): Boolean {
            return ENDPOINTS.contains(value)
        }
    }
}