package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository

import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao.UserDao
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.UserEntity
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.api.ApiService
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.AuthLoginRequest
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.User
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = apiService.login(AuthLoginRequest(email, password))
            if (response.isSuccessful) {
                val body = response.body()!!
                val user = User(
                    id = body.user.id,
                    name = body.user.name,
                    email = body.user.email,
                    age = 0
                )
                // Persistir en Room (upsert)
                userDao.insertUser(
                    UserEntity(
                        id = user.id,
                        name = user.name,
                        email = user.email,
                        age = user.age
                    )
                )
                Result.success(user)
            } else {
                Result.failure(Exception("Error de login: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


