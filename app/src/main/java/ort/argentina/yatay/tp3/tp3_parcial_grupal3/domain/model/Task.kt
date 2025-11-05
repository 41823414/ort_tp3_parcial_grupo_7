package ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model

/**
 * DOMAIN MODEL - Task
 * Modelo de tarea en la capa de dominio
 * No tiene anotaciones de base de datos, es independiente de la persistencia
 */
data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val userId: Int,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

