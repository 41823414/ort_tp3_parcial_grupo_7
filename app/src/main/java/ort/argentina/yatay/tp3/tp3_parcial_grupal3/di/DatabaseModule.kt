package ort.argentina.yatay.tp3.tp3_parcial_grupal3.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.AppDatabase
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao.TaskDao
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.local.dao.UserDao
import javax.inject.Singleton

/**
 * Módulo de Hilt para proveer la base de datos Room y los DAOs
 * Integración de Room con Dagger/Hilt
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provee la instancia de la base de datos
     * @Singleton asegura una única instancia en toda la app
     */
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration() // En producción, usa migraciones apropiadas
            .build()
    }

    /**
     * Provee el UserDao desde la base de datos
     */
    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    /**
     * Provee el TaskDao desde la base de datos
     */
    @Provides
    @Singleton
    fun provideTaskDao(database: AppDatabase): TaskDao {
        return database.taskDao()
    }
}

