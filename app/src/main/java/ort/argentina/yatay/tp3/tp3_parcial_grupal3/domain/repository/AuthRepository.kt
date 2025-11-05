package ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository

import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>

    suspend fun signUp(username: String, email: String, password: String): Result<User>
}


