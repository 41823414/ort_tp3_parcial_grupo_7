package ort.argentina.yatay.tp3.tp3_parcial_grupal3.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Módulo principal de la aplicación usando Dagger/Hilt
 * @Module indica que esta clase provee dependencias
 * @InstallIn(SingletonComponent::class) indica que las dependencias viven durante toda la app
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Ejemplo: Proveer Dispatchers de Coroutines
     * @Provides indica que este método provee una dependencia
     * Los qualifiers permiten diferenciar entre múltiples implementaciones
     */
    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    /**
     * Ejemplo: Proveer constantes de configuración
     */
    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String = "https://api.ejemplo.com/"

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(): String = "tu_api_key_aqui"

    // Aquí puedes agregar más proveedores:
    // - Retrofit para llamadas HTTP
    // - Room Database
    // - OkHttpClient
    // - Gson/Moshi para JSON
    // etc.

    /**
     * Ejemplo de cómo proveer Retrofit (comentado):
     *
     * @Provides
     * @Singleton
     * fun provideRetrofit(@BaseUrl baseUrl: String): Retrofit {
     *     return Retrofit.Builder()
     *         .baseUrl(baseUrl)
     *         .addConverterFactory(GsonConverterFactory.create())
     *         .build()
     * }
     */
}

