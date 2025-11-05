package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository

import android.util.Log
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.base.BaseRepository
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao.UserDao
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.mapper.AuthMapper
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.mapper.UserMapper
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.api.ApiService
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.AuthLoginRequest
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.AuthSignUpRequest
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.User
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * DATA REPOSITORY IMPLEMENTATION - AuthRepositoryImpl
 * Implementa la interfaz AuthRepository de la capa de dominio
 * Usa mappers para convertir entre DTOs, entidades de datos y modelos de dominio
 */
@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : BaseRepository(), AuthRepository {

    override suspend fun login(username: String, password: String): Result<User> {
        return safeApiCall {
            val response = apiService.login(AuthLoginRequest(username, password))
            if (response.isSuccessful || response.code() == 201) { // 201 Created según API real
                val body = response.body()!!
                Log.d("AuthRepository", "Retrofit OK: token=${body.token}")
                
                // La API solo retorna token, no datos del usuario
                // Crear usuario temporal con el username para persistir en Room
                // TODO: Hacer llamada a GET /users/{id} si se necesita el usuario completo
                val user = User(
                    id = 0, // Se asignará automáticamente por Room (autoGenerate)
                    name = username,
                    email = "", // No disponible en la respuesta de login
                    age = 0
                )
                
                // Persistir en Room usando UserMapper
                val userId = userDao.insertUser(
                    UserMapper.run { user.toEntity() }
                )
                val count = userDao.getUserCount()
                Log.d("AuthRepository", "Room OK: insert userId=$userId, totalUsers=$count, token=${body.token}")
                
                // Retornar usuario con el ID asignado por Room
                user.copy(id = userId.toInt())
            } else {
                throw Exception("Error de login: ${response.code()}")
            }
        }
    }

    override suspend fun signUp(username: String, email: String, password: String): Result<User> {
        return safeApiCall {
            val response = apiService.signUp(AuthSignUpRequest(
                id = null,
                username = username,
                email = email,
                password = password
            ))
            if (response.isSuccessful) {
                val body = response.body()!!
                Log.d("AuthRepository", "Retrofit OK SignUp: userId=${body.id}, username=${body.username}, email=${body.email}")
                
                // Mapear AuthSignUpResponse (DTO) a User (domain) usando AuthMapper
                val user = AuthMapper.run { body.toDomain() }
                
                // Persistir en Room usando UserMapper
                val userId = userDao.insertUser(
                    UserMapper.run { user.toEntity() }
                )
                val count = userDao.getUserCount()
                Log.d("AuthRepository", "Room OK SignUp: insert userId=$userId, totalUsers=$count")
                
                user
            } else {
                throw Exception("Error de signUp: ${response.code()}")
            }
        }
    }
}


