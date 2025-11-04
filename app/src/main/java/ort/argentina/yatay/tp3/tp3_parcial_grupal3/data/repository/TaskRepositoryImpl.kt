package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao.TaskDao
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.mapper.TaskMapper
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.Task
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository.TaskRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * DATA REPOSITORY IMPLEMENTATION - TaskRepositoryImpl
 * Implementa la interfaz TaskRepository de la capa de dominio
 */
@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks().map { entities ->
            TaskMapper.run { entities.toDomain() }
        }
    }

    override suspend fun getTaskById(taskId: Int): Task? {
        // El DAO no tiene getTaskById, necesitaríamos agregarlo
        // Por ahora retornamos null
        return null
    }

    override fun getTasksByUser(userId: Int): Flow<List<Task>> {
        return taskDao.getTasksByUserId(userId).map { entities ->
            TaskMapper.run { entities.toDomain() }
        }
    }

    override fun getCompletedTasks(): Flow<List<Task>> {
        return taskDao.getCompletedTasks().map { entities ->
            TaskMapper.run { entities.toDomain() }
        }
    }

    override fun getPendingTasks(): Flow<List<Task>> {
        return taskDao.getPendingTasks().map { entities ->
            TaskMapper.run { entities.toDomain() }
        }
    }

    override suspend fun insertTask(task: Task): Long {
        return taskDao.insertTask(TaskMapper.run { task.toEntity() })
    }

    override suspend fun insertTasks(tasks: List<Task>) {
        taskDao.insertTasks(TaskMapper.run { tasks.toEntity() })
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(TaskMapper.run { task.toEntity() })
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(TaskMapper.run { task.toEntity() })
    }

    override suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }

    override suspend fun deleteTasksByUser(userId: Int) {
        // El DAO no tiene este método directo
        // Por ahora, obtenemos las tareas y las eliminamos una por una
        taskDao.getTasksByUserId(userId).collect { taskEntities ->
            taskEntities.forEach { taskEntity ->
                taskDao.deleteTask(taskEntity)
            }
        }
    }
}

