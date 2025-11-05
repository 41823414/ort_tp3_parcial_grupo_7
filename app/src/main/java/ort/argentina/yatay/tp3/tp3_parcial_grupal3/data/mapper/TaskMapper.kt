package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.mapper

import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.TaskEntity
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.Task

/**
 * DATA MAPPER - TaskMapper
 * Convierte entre entidades de datos (TaskEntity) y modelos de dominio (Task)
 */
object TaskMapper {
    
    /**
     * Convertir TaskEntity a Task (dominio)
     */
    fun TaskEntity.toDomain(): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted,
            userId = userId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
    
    /**
     * Convertir Task (dominio) a TaskEntity
     */
    fun Task.toEntity(): TaskEntity {
        return TaskEntity(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted,
            userId = userId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
    
    /**
     * Convertir lista de TaskEntity a lista de Task
     */
    fun List<TaskEntity>.toDomain(): List<Task> {
        return map { it.toDomain() }
    }
    
    /**
     * Convertir lista de Task a lista de TaskEntity
     */
    fun List<Task>.toEntity(): List<TaskEntity> {
        return map { it.toEntity() }
    }
}

