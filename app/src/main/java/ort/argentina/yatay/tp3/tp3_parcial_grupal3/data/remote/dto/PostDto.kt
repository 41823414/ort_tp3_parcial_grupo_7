package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO para Post (publicación)
 */
data class PostDto(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String
)

