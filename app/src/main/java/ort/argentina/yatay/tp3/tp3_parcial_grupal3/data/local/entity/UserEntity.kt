data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "age")
    val age: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

/**
 * Entidad de ejemplo para Room
 * @Entity define una tabla en la base de datos
 * Cada propiedad se convierte en una columna
 */
@Entity(tableName = "users")

