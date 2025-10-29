package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository

import kotlinx.coroutines.flow.Flow
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao.UserDao
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositorio para operaciones de usuarios usando Room
 * Inyecta UserDao automáticamente con Hilt
 */
@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    /**
     * Obtener todos los usuarios como Flow
     */
    fun getAllUsers(): Flow<List<UserEntity>> {
        return userDao.getAllUsers()
    }

    /**
     * Obtener usuario por ID
     */
    suspend fun getUserById(userId: Int): UserEntity? {
        return userDao.getUserById(userId)
    }

    /**
     * Buscar usuarios por nombre
     */
    fun searchUsers(query: String): Flow<List<UserEntity>> {
        return userDao.searchUsersByName(query)
    }

    /**
     * Insertar nuevo usuario
     */
    suspend fun insertUser(user: UserEntity): Long {
        return userDao.insertUser(user)
    }

    /**
     * Insertar múltiples usuarios
     */
    suspend fun insertUsers(users: List<UserEntity>) {
        userDao.insertUsers(users)
    }

    /**
     * Actualizar usuario existente
     */
    suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

    /**
     * Eliminar usuario
     */
    suspend fun deleteUser(user: UserEntity) {
        userDao.deleteUser(user)
    }

    /**
     * Eliminar todos los usuarios
     */
    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    /**
     * Obtener conteo de usuarios
     */
    suspend fun getUserCount(): Int {
        return userDao.getUserCount()
    }
}

