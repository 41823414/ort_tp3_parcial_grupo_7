package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.mapper

import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.AuthSignUpResponse
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.User

/**
 * DATA MAPPER - AuthMapper
 * Convierte entre DTOs de autenticación (AuthSignUpResponse) y modelos de dominio (User)
 */
object AuthMapper {
    
    /**
     * Convertir AuthSignUpResponse (DTO) a User (dominio)
     */
    fun AuthSignUpResponse.toDomain(): User {
        return User(
            id = id,
            name = "${name.firstname} ${name.lastname}".trim(),
            email = email,
            age = 0 // No disponible en la respuesta de signUp
        )
    }
}
