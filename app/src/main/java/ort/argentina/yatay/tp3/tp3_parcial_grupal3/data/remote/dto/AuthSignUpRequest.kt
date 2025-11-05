package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto

/**
 * Request para el endpoint POST /auth/create
 * Según la colección de Postman
 */
data class AuthSignUpRequest(
    val id: Int? = null, // Opcional, puede ser null
    val username: String,
    val email: String,
    val password: String
)

