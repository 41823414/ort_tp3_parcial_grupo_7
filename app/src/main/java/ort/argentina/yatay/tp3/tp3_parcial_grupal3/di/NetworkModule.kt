package ort.argentina.yatay.tp3.tp3_parcial_grupal3.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Módulo de Hilt para proveer Retrofit y componentes de networking
 * Integración completa de Retrofit + OkHttp + Gson con Hilt
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Base URL de la API
     * Usando JSONPlaceholder como API de ejemplo
     */
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    /**
     * Provee Gson configurado
     */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    /**
     * Provee HttpLoggingInterceptor para debugging
     * Muestra logs de las peticiones HTTP
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provee OkHttpClient configurado
     * Cliente HTTP con interceptors y timeouts
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provee Retrofit configurado
     * Framework para hacer peticiones HTTP
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /**
     * Provee ApiService
     * Interfaz con los endpoints de la API
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

