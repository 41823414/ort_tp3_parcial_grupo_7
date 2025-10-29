package ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * GENERICS - Repository Base Genérico
 *
 * BaseRepository proporciona funciones helper comunes para repositories
 * Maneja la ejecución en IO dispatcher y captura de errores
 */
abstract class BaseRepository {

    /**
     * Ejecutar operación en IO dispatcher con manejo de errores
     * Retorna Result<T> genérico
     */
    protected suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Result<T> = withContext(Dispatchers.IO) {
        try {
            Result.success(apiCall())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Ejecutar operación y retornar datos o null
     */
    protected suspend fun <T> executeOrNull(
        operation: suspend () -> T
    ): T? = withContext(Dispatchers.IO) {
        try {
            operation()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Ejecutar operación con manejo de errores personalizado
     */
    protected suspend fun <T> execute(
        operation: suspend () -> T,
        onError: (Exception) -> T
    ): T = withContext(Dispatchers.IO) {
        try {
            operation()
        } catch (e: Exception) {
            onError(e)
        }
    }
}

