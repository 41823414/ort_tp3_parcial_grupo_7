package ort.argentina.yatay.tp3.tp3_parcial_grupal3.di

import javax.inject.Qualifier

/**
 * Qualifiers personalizados para Dagger/Hilt
 * Permiten distinguir entre diferentes implementaciones del mismo tipo
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiKey

