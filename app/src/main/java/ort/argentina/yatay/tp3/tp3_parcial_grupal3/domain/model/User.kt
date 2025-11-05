package ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model

/**
 * DOMAIN MODEL - User
 * Modelo de usuario en la capa de dominio
 * No tiene anotaciones de base de datos, es independiente de la persistencia
 */
data class User(
    val id: Int = 0,
    val name: String,
    val email: String,
    val age: Int,
    val createdAt: Long = System.currentTimeMillis()
)

