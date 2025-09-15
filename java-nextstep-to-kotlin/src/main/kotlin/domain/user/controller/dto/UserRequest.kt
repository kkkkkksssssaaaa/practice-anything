package domain.user.controller.dto

data class CreateUserRequest(
    val userId: String,
    val name: String,
    val password: String,
    val email: String,
)