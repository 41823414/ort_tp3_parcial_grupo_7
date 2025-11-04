package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.mapper

import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.UserEntity
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.relations.UserWithTasks as UserWithTasksData
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.User
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.UserWithTasks

/**
 * DATA MAPPER - UserMapper
 * Convierte entre entidades de datos (UserEntity) y modelos de dominio (User)
 */
object UserMapper {
    
    /**
     * Convertir UserEntity a User (dominio)
     */
    fun UserEntity.toDomain(): User {
        return User(
            id = id,
            name = name,
            email = email,
            age = age,
            createdAt = createdAt
        )
    }
    
    /**
     * Convertir User (dominio) a UserEntity
     */
    fun User.toEntity(): UserEntity {
        return UserEntity(
            id = id,
            name = name,
            email = email,
            age = age,
            createdAt = createdAt
        )
    }
    
    /**
     * Convertir lista de UserEntity a lista de User
     */
    fun List<UserEntity>.toDomain(): List<User> {
        return map { it.toDomain() }
    }
    
    /**
     * Convertir lista de User a lista de UserEntity
     */
    fun List<User>.toEntity(): List<UserEntity> {
        return map { it.toEntity() }
    }
    
    /**
     * Convertir UserWithTasks (entidad) a UserWithTasks (dominio)
     */
    fun UserWithTasksData.toDomain(): UserWithTasks {
        return UserWithTasks(
            user = user.toDomain(),
            tasks = tasks.map { TaskMapper.run { it.toDomain() } }
        )
    }
    
    /**
     * Convertir lista de UserWithTasksData a lista de UserWithTasks
     */
    fun List<UserWithTasksData>.toDomainList(): List<UserWithTasks> {
        return map { it.toDomain() }
    }
}

