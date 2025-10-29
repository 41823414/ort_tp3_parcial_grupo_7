package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.TaskEntity
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.UserEntity

/**
 * Relación uno a muchos: Un usuario tiene muchas tareas
 * @Embedded incluye todos los campos de UserEntity
 * @Relation define la relación entre las tablas
 */
data class UserWithTasks(
    @Embedded val user: UserEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val tasks: List<TaskEntity>
)

