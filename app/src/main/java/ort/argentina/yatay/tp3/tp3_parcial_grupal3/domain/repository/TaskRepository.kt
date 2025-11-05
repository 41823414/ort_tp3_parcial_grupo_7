package ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository

import kotlinx.coroutines.flow.Flow
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.Task

/**
 * DOMAIN REPOSITORY INTERFACE - TaskRepository
 * Define el contrato para operaciones de tareas
 * La implementación debe estar en la capa de datos
 */
interface TaskRepository {

    /**
     * Obtener todas las tareas
     */
    fun getAllTasks(): Flow<List<Task>>

    /**
     * Obtener tarea por ID
     */
    suspend fun getTaskById(taskId: Int): Task?

    /**
     * Obtener tareas de un usuario
     */
    fun getTasksByUser(userId: Int): Flow<List<Task>>

    /**
     * Obtener tareas completadas
     */
    fun getCompletedTasks(): Flow<List<Task>>

    /**
     * Obtener tareas pendientes
     */
    fun getPendingTasks(): Flow<List<Task>>

    /**
     * Insertar una tarea
     */
    suspend fun insertTask(task: Task): Long

    /**
     * Insertar múltiples tareas
     */
    suspend fun insertTasks(tasks: List<Task>)

    /**
     * Actualizar una tarea
     */
    suspend fun updateTask(task: Task)

    /**
     * Eliminar una tarea
     */
    suspend fun deleteTask(task: Task)

    /**
     * Eliminar tareas de un usuario
     */
    suspend fun deleteTasksByUser(userId: Int)

    /**
     * Marcar tarea como completada
     */
    suspend fun markTaskAsCompleted(taskId: Int)

    /**
     * Eliminar todas las tareas
     */
    suspend fun deleteAllTasks()

    /**
     * Eliminar tareas completadas
     */
    suspend fun deleteCompletedTasks()
}

