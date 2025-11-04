package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto

/**
 * Respuesta del endpoint /auth/login
 * Según la API real, solo retorna el token JWT
 */
data class AuthLoginResponse(
    val token: String
)


