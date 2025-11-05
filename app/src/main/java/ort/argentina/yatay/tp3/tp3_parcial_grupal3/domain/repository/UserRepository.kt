package ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository

import kotlinx.coroutines.flow.Flow
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.User
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.UserWithTasks

/**
 * DOMAIN REPOSITORY INTERFACE - UserRepository
 * Define el contrato para operaciones de usuarios
 * La implementación debe estar en la capa de datos
 * Usa modelos de dominio (User), no entidades (UserEntity)
 */
interface UserRepository {

    /**
     * Obtener todos los usuarios como Flow
     */
    fun getAllUsers(): Flow<List<User>>

    /**
     * Obtener usuario por ID
     */
    suspend fun getUserById(userId: Int): User?

    /**
     * Buscar usuarios por nombre
     */
    fun searchUsers(query: String): Flow<List<User>>

    /**
     * Obtener usuarios por rango de edad
     */
    fun getUsersByAgeRange(minAge: Int, maxAge: Int): Flow<List<User>>

    /**
     * Insertar un usuario
     */
    suspend fun insertUser(user: User): Long

    /**
     * Insertar múltiples usuarios
     */
    suspend fun insertUsers(users: List<User>)

    /**
     * Actualizar un usuario
     */
    suspend fun updateUser(user: User)

    /**
     * Eliminar un usuario
     */
    suspend fun deleteUser(user: User)

    /**
     * Eliminar todos los usuarios
     */
    suspend fun deleteAllUsers()

    /**
     * Obtener cantidad de usuarios
     */
    suspend fun getUserCount(): Int

    /**
     * Obtener usuario con sus tareas
     */
    suspend fun getUserWithTasks(userId: Int): UserWithTasks?

    /**
     * Obtener todos los usuarios con sus tareas
     */
    fun getUsersWithTasks(): Flow<List<UserWithTasks>>
}

