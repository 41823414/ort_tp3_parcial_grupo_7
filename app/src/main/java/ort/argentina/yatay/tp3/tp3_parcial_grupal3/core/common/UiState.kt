package ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.common

/**
 * GENERICS - Clase genérica para manejo de estados de UI simplificado
 *
 * UiState<T> representa el estado de una pantalla con datos de tipo T
 */
sealed class UiState<out T> {
    /**
     * Idle - Estado inicial, sin datos
     */
    object Idle : UiState<Nothing>()

    /**
     * Loading - Cargando datos
     */
    object Loading : UiState<Nothing>()

    /**
     * Success - Datos cargados exitosamente
     */
    data class Success<out T>(val data: T) : UiState<T>()

    /**
     * Error - Error al cargar datos
     */
    data class Error(val message: String, val exception: Exception? = null) : UiState<Nothing>()

    /**
     * Empty - Sin datos disponibles (pero no es un error)
     */
    object Empty : UiState<Nothing>()
}

/**
 * Extensiones para facilitar el uso
 */
fun <T> UiState<T>.isLoading() = this is UiState.Loading
fun <T> UiState<T>.isSuccess() = this is UiState.Success
fun <T> UiState<T>.isError() = this is UiState.Error
fun <T> UiState<T>.isEmpty() = this is UiState.Empty

/**
 * Obtener datos si es Success
 */
fun <T> UiState<T>.dataOrNull(): T? = (this as? UiState.Success)?.data

