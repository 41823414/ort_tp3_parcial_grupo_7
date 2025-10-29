package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository

import kotlinx.coroutines.flow.Flow
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao.TaskDao
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.TaskEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositorio para operaciones de tareas usando Room
 */
@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDao.getAllTasks()
    }

    fun getTasksByUserId(userId: Int): Flow<List<TaskEntity>> {
        return taskDao.getTasksByUserId(userId)
    }

    fun getCompletedTasks(): Flow<List<TaskEntity>> {
        return taskDao.getCompletedTasks()
    }

    fun getPendingTasks(): Flow<List<TaskEntity>> {
        return taskDao.getPendingTasks()
    }

    suspend fun insertTask(task: TaskEntity): Long {
        return taskDao.insertTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }

    suspend fun markTaskAsCompleted(taskId: Int) {
        taskDao.markTaskAsCompleted(taskId)
    }

    suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }

    suspend fun deleteCompletedTasks() {
        taskDao.deleteCompletedTasks()
    }
}

