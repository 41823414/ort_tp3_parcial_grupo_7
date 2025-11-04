package ort.argentina.yatay.tp3.tp3_parcial_grupal3.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository.ExampleRepository
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository.ExampleRepositoryInterface
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository.TaskRepositoryImpl
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository.UserRepositoryImpl
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository.AuthRepositoryImpl
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository.TaskRepository
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository.UserRepository
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository.AuthRepository
import javax.inject.Singleton

/**
 * Módulo que usa @Binds para vincular interfaces de dominio con implementaciones de datos
 * Este es el patrón de Clean Architecture: domain define contratos, data los implementa
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Vincula ExampleRepository a ExampleRepositoryInterface (mantiene compatibilidad)
     */
    @Binds
    @Singleton
    abstract fun bindExampleRepository(
        implementation: ExampleRepository
    ): ExampleRepositoryInterface

    /**
     * Vincula UserRepositoryImpl a UserRepository (interfaz de dominio)
     * Clean Architecture: domain.repository.UserRepository -> data.repository.UserRepositoryImpl
     */
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        implementation: UserRepositoryImpl
    ): UserRepository

    /**
     * Vincula TaskRepositoryImpl a TaskRepository (interfaz de dominio)
     * Clean Architecture: domain.repository.TaskRepository -> data.repository.TaskRepositoryImpl
     */
    @Binds
    @Singleton
    abstract fun bindTaskRepository(
        implementation: TaskRepositoryImpl
    ): TaskRepository

    /**
     * Vincula AuthRepositoryImpl a AuthRepository (dominio)
     */
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        implementation: AuthRepositoryImpl
    ): AuthRepository
}

