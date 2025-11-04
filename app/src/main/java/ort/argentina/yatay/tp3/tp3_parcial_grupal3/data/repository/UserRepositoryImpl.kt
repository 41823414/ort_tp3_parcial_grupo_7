package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao.UserDao
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.mapper.UserMapper
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.User
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.UserWithTasks
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * DATA REPOSITORY IMPLEMENTATION - UserRepositoryImpl
 * Implementa la interfaz UserRepository de la capa de dominio
 * Usa mappers para convertir entre entidades de datos y modelos de dominio
 */
@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { entities ->
            UserMapper.run { entities.toDomain() }
        }
    }

    override suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)?.let { UserMapper.run { it.toDomain() } }
    }

    override fun searchUsers(query: String): Flow<List<User>> {
        return userDao.searchUsersByName(query).map { entities ->
            UserMapper.run { entities.toDomain() }
        }
    }

    override fun getUsersByAgeRange(minAge: Int, maxAge: Int): Flow<List<User>> {
        return userDao.getUsersByAgeRange(minAge, maxAge).map { entities ->
            UserMapper.run { entities.toDomain() }
        }
    }

    override suspend fun insertUser(user: User): Long {
        return userDao.insertUser(UserMapper.run { user.toEntity() })
    }

    override suspend fun insertUsers(users: List<User>) {
        userDao.insertUsers(UserMapper.run { users.toEntity() })
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(UserMapper.run { user.toEntity() })
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(UserMapper.run { user.toEntity() })
    }

    override suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    override suspend fun getUserCount(): Int {
        return userDao.getUserCount()
    }

    override fun getUsersWithTasks(): Flow<List<UserWithTasks>> {
        return userDao.getUsersWithTasks().map { entities ->
            UserMapper.run { entities.toDomainList() }
        }
    }

    override suspend fun getUserWithTasks(userId: Int): UserWithTasks? {
        return userDao.getUserWithTasks(userId)?.let { UserMapper.run { it.toDomain() } }
    }
}

