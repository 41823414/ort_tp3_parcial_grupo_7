package ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model

/**
 * DOMAIN MODEL - UserWithTasks
 * Modelo que representa un usuario con sus tareas asociadas
 * Esta es la relación uno a muchos en la capa de dominio
 */
data class UserWithTasks(
    val user: User,
    val tasks: List<Task> = emptyList()
)

