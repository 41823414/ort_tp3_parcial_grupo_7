package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto

/**
 * Response del endpoint POST /auth/create
 * Según la colección de Postman, retorna el usuario completo
 */
data class AuthSignUpResponse(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val name: UserNameDto,
    val address: UserAddressDto?,
    val phone: String?
)

data class UserNameDto(
    val firstname: String,
    val lastname: String
)

data class UserAddressDto(
    val geolocation: GeolocationDto?,
    val city: String?,
    val street: String?,
    val number: Int?,
    val zipcode: String?
)

data class GeolocationDto(
    val lat: String,
    val long: String
)

