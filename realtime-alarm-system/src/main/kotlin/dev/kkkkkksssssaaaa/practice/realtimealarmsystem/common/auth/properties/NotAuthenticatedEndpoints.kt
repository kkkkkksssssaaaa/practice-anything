package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.properties

class NotAuthenticatedEndpoints {
    companion object {
        const val LOGIN = "/auth/login"
        const val REGISTRATION = "/users/registration"
        const val TOKEN_REFRESH = "/auth/token/refresh"

        private val ENDPOINTS = listOf(
            LOGIN,
            REGISTRATION,
            TOKEN_REFRESH
        )

        fun toList(): List<String> {
            return ENDPOINTS
        }

        fun contains(value: String): Boolean {
            return ENDPOINTS.contains(value)
        }
    }
}