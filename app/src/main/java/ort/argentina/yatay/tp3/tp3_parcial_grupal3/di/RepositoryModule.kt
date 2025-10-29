package ort.argentina.yatay.tp3.tp3_parcial_grupal3.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository.ExampleRepository
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository.ExampleRepositoryInterface
import javax.inject.Singleton

/**
 * Módulo que usa @Binds en lugar de @Provides
 * @Binds es más eficiente cuando solo necesitas vincular una interfaz a su implementación
 * Este es un patrón común en Dagger/Hilt para abstraer repositorios
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Vincula la implementación ExampleRepository a la interfaz ExampleRepositoryInterface
     * Dagger/Hilt automáticamente sabrá que cuando se requiera ExampleRepositoryInterface,
     * debe proveer una instancia de ExampleRepository
     */
    @Binds
    @Singleton
    abstract fun bindExampleRepository(
        implementation: ExampleRepository
    ): ExampleRepositoryInterface
}

