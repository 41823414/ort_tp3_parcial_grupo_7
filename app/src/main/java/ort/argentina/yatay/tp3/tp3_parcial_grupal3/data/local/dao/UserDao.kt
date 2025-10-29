package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.UserEntity

/**
 * DAO (Data Access Object) para UserEntity
 * Define las operaciones de base de datos para la tabla users
 */
@Dao
interface UserDao {

    /**
     * Insertar un usuario
     * OnConflictStrategy.REPLACE reemplaza si ya existe
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    /**
     * Insertar múltiples usuarios
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    /**
     * Actualizar un usuario existente
     */
    @Update
    suspend fun updateUser(user: UserEntity)

    /**
     * Eliminar un usuario
     */
    @Delete
    suspend fun deleteUser(user: UserEntity)

    /**
     * Obtener todos los usuarios como Flow
     * Flow permite observar cambios en tiempo real
     */
    @Query("SELECT * FROM users ORDER BY created_at DESC")
    fun getAllUsers(): Flow<List<UserEntity>>

    /**
     * Obtener un usuario por ID
     */
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): UserEntity?

    /**
     * Buscar usuarios por nombre
     */
    @Query("SELECT * FROM users WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchUsersByName(searchQuery: String): Flow<List<UserEntity>>

    /**
     * Obtener usuarios por rango de edad
     */
    @Query("SELECT * FROM users WHERE age BETWEEN :minAge AND :maxAge")
    fun getUsersByAgeRange(minAge: Int, maxAge: Int): Flow<List<UserEntity>>

    /**
     * Eliminar todos los usuarios
     */
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    /**
     * Contar usuarios
     */
    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int

    /**
     * Obtener usuarios con sus tareas (relación uno a muchos)
     * @Transaction asegura que la operación sea atómica
     */
    @Transaction
    @Query("SELECT * FROM users")
    fun getUsersWithTasks(): Flow<List<UserWithTasks>>

    /**
     * Obtener un usuario específico con sus tareas
     */
    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserWithTasks(userId: Int): UserWithTasks?
}
package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.UserEntity
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.relations.UserWithTasks

/**
 * DAO (Data Access Object) para UserEntity
 * Define las operaciones de base de datos para la tabla users
 */
@Dao
interface UserDao {

    /**
     * Insertar un usuario
     * OnConflictStrategy.REPLACE reemplaza si ya existe
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    /**
     * Insertar múltiples usuarios
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    /**
     * Actualizar un usuario existente
     */
    @Update
    suspend fun updateUser(user: UserEntity)

    /**
     * Eliminar un usuario
     */
    @Delete
    suspend fun deleteUser(user: UserEntity)

    /**
     * Obtener todos los usuarios como Flow
     * Flow permite observar cambios en tiempo real
     */
    @Query("SELECT * FROM users ORDER BY created_at DESC")
    fun getAllUsers(): Flow<List<UserEntity>>

    /**
     * Obtener un usuario por ID
     */
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): UserEntity?

    /**
     * Buscar usuarios por nombre
     */
    @Query("SELECT * FROM users WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchUsersByName(searchQuery: String): Flow<List<UserEntity>>

    /**
     * Obtener usuarios por rango de edad
     */
    @Query("SELECT * FROM users WHERE age BETWEEN :minAge AND :maxAge")
    fun getUsersByAgeRange(minAge: Int, maxAge: Int): Flow<List<UserEntity>>

    /**
     * Eliminar todos los usuarios
     */
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    /**
     * Contar usuarios
     */
    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int
}

