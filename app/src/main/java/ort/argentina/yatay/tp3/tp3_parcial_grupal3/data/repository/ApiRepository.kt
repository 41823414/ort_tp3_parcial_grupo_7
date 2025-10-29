package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.api.ApiService
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.PostDto
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.UserDto
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositorio para operaciones de API con Retrofit
 * Maneja las llamadas a la API y el manejo de errores
 */
@Singleton
class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {

    /**
     * Obtener lista de usuarios desde la API
     */
    suspend fun getUsers(): Result<List<UserDto>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getUsers()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtener un usuario por ID
     */
    suspend fun getUserById(userId: Int): Result<UserDto> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getUserById(userId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtener lista de posts
     */
    suspend fun getPosts(): Result<List<PostDto>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getPosts()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtener posts de un usuario específico
     */
    suspend fun getPostsByUser(userId: Int): Result<List<PostDto>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getPostsByUser(userId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Crear un nuevo post
     */
    suspend fun createPost(post: PostDto): Result<PostDto> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.createPost(post)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

