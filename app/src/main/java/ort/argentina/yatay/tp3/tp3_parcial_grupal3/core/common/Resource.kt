package ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.common

/**
 * GENERICS - Clase genérica para manejar estados de UI
 *
 * Resource<T> es un sealed class genérico que encapsula:
 * - Success: datos exitosos de tipo T
 * - Error: errores con mensaje
 * - Loading: estado de carga
 *
 * Ejemplo de uso:
 * Resource<List<User>>
 * Resource<String>
 * Resource<User>
 */
sealed class Resource<out T> {
    /**
     * Estado de éxito con datos de tipo T
     */
    data class Success<out T>(val data: T) : Resource<T>()

    /**
     * Estado de error con mensaje
     */
    data class Error(val message: String, val throwable: Throwable? = null) : Resource<Nothing>()

    /**
     * Estado de carga (opcional con datos previos)
     */
    data class Loading<out T>(val data: T? = null) : Resource<T>()

    /**
     * Estado inicial
     */
    object Initial : Resource<Nothing>()

    companion object {
        /**
         * Función helper para crear Success
         */
        fun <T> success(data: T): Resource<T> = Success(data)

        /**
         * Función helper para crear Error
         */
        fun error(message: String, throwable: Throwable? = null): Resource<Nothing> =
            Error(message, throwable)

        /**
         * Función helper para crear Loading
         */
        fun <T> loading(data: T? = null): Resource<T> = Loading(data)
    }
}

/**
 * Extensiones útiles para Resource
 */
val <T> Resource<T>.isSuccess: Boolean
    get() = this is Resource.Success

val <T> Resource<T>.isError: Boolean
    get() = this is Resource.Error

val <T> Resource<T>.isLoading: Boolean
    get() = this is Resource.Loading

/**
 * Obtener datos si está en Success, null en otro caso
 */
fun <T> Resource<T>.getDataOrNull(): T? {
    return when (this) {
        is Resource.Success -> data
        is Resource.Loading -> data
        else -> null
    }
}

/**
 * Transformar datos de Resource<T> a Resource<R>
 */
inline fun <T, R> Resource<T>.map(transform: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Error -> Resource.Error(message, throwable)
        is Resource.Loading -> Resource.Loading(data?.let(transform))
        is Resource.Initial -> Resource.Initial
    }
}

