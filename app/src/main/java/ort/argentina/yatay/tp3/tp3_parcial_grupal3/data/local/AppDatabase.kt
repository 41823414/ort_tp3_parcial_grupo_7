package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao.TaskDao
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao.UserDao
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.TaskEntity
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.entity.UserEntity

/**
 * Base de datos principal de la aplicación usando Room
 *
 * @Database define las entidades y la versión de la BD
 * - entities: lista de todas las entidades (tablas)
 * - version: número de versión para migraciones
 * - exportSchema: genera JSON con el esquema de la BD
 */
@Database(
    entities = [
        UserEntity::class,
        TaskEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Métodos abstractos que Room implementa automáticamente
     */
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}

