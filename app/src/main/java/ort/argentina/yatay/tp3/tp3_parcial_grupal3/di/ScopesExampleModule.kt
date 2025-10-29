package ort.argentina.yatay.tp3.tp3_parcial_grupal3.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * EJEMPLOS DE DIFERENTES SCOPES EN DAGGER/HILT
 *
 * Hilt proporciona varios componentes con diferentes ciclos de vida:
 *
 * 1. SingletonComponent - Vive durante toda la aplicación
 * 2. ActivityComponent - Vive durante el ciclo de vida de la Activity
 * 3. ViewModelComponent - Vive durante el ciclo de vida del ViewModel
 * 4. FragmentComponent - Vive durante el ciclo de vida del Fragment
 * 5. ServiceComponent - Vive durante el ciclo de vida del Service
 */

/**
 * Módulo con scope de Activity
 * Las dependencias aquí se crean por cada Activity
 */
@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    @ActivityScoped
    fun provideActivityScopedString(): String {
        return "Esta instancia vive durante la Activity"
    }
}

/**
 * Módulo con scope de ViewModel
 * Las dependencias aquí se crean por cada ViewModel
 */
@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideViewModelScopedString(): String {
        return "Esta instancia vive durante el ViewModel"
    }
}

