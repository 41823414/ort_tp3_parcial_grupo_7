package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.TaskEntity

/**
 * DAO para TaskEntity
 */
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskEntity>)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    /**
     * Obtener todas las tareas
     */
    @Query("SELECT * FROM tasks ORDER BY created_at DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    /**
     * Obtener tareas de un usuario específico
     */
    @Query("SELECT * FROM tasks WHERE user_id = :userId ORDER BY created_at DESC")
    fun getTasksByUserId(userId: Int): Flow<List<TaskEntity>>

    /**
     * Obtener tareas completadas
     */
    @Query("SELECT * FROM tasks WHERE is_completed = 1")
    fun getCompletedTasks(): Flow<List<TaskEntity>>

    /**
     * Obtener tareas pendientes
     */
    @Query("SELECT * FROM tasks WHERE is_completed = 0")
    fun getPendingTasks(): Flow<List<TaskEntity>>

    /**
     * Marcar tarea como completada
     */
    @Query("UPDATE tasks SET is_completed = 1, updated_at = :updatedAt WHERE id = :taskId")
    suspend fun markTaskAsCompleted(taskId: Int, updatedAt: Long = System.currentTimeMillis())

    /**
     * Eliminar todas las tareas
     */
    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()

    /**
     * Eliminar tareas completadas
     */
    @Query("DELETE FROM tasks WHERE is_completed = 1")
    suspend fun deleteCompletedTasks()
}

