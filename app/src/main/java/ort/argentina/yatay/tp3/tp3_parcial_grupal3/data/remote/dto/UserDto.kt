package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO (Data Transfer Object) para Usuario
 * Representa la respuesta de la API
 */
data class UserDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("website")
    val website: String? = null,

    @SerializedName("address")
    val address: AddressDto? = null,

    @SerializedName("company")
    val company: CompanyDto? = null
)

data class AddressDto(
    @SerializedName("street")
    val street: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("zipcode")
    val zipcode: String
)

data class CompanyDto(
    @SerializedName("name")
    val name: String
)

