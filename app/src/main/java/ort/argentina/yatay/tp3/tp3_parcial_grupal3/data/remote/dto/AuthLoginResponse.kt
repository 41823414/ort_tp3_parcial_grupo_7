package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto

data class AuthLoginResponse(
    val token: String,
    val user: LoginUserDto
)

data class LoginUserDto(
    val id: Int,
    val name: String,
    val email: String
)


