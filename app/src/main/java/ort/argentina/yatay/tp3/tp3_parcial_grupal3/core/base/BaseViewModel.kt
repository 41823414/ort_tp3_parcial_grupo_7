package ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.common.Resource

/**
 * GENERICS + VIEWMODEL - Base ViewModel genérico
 *
 * BaseViewModel<T> proporciona funcionalidades comunes para todos los ViewModels
 * - Manejo de estados con Resource<T>
 * - Manejo de errores centralizado
 * - Funciones helper para operaciones comunes
 *
 * @param T El tipo de datos que maneja este ViewModel
 */
abstract class BaseViewModel<T> : ViewModel() {

    // Estado genérico usando Resource<T>
    protected val _state = MutableStateFlow<Resource<T>>(Resource.Initial)
    val state: StateFlow<Resource<T>> = _state.asStateFlow()

    // Estado de loading separado (opcional)
    protected val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Estado de error separado (opcional)
    protected val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    /**
     * Exception Handler para capturar errores no manejados
     */
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    /**
     * Ejecutar una operación suspend con manejo de errores automático
     */
    protected fun <R> launchDataLoad(
        onLoading: () -> Unit = { _state.value = Resource.Loading() },
        onError: (Throwable) -> Unit = { handleError(it) },
        block: suspend () -> R
    ) {
        viewModelScope.launch(exceptionHandler) {
            try {
                onLoading()
                block()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    /**
     * Ejecutar operación y actualizar estado automáticamente
     */
    protected fun executeWithResource(
        block: suspend () -> T
    ) {
        viewModelScope.launch(exceptionHandler) {
            _state.value = Resource.Loading()
            try {
                val result = block()
                _state.value = Resource.Success(result)
            } catch (e: Exception) {
                _state.value = Resource.Error(
                    message = e.message ?: "Error desconocido",
                    throwable = e
                )
            }
        }
    }

    /**
     * Manejar errores de forma centralizada
     */
    protected open fun handleError(throwable: Throwable) {
        val errorMessage = throwable.message ?: "Error desconocido"
        _error.value = errorMessage
        _state.value = Resource.Error(errorMessage, throwable)
        _isLoading.value = false
    }

    /**
     * Limpiar error
     */
    fun clearError() {
        _error.value = null
    }

    /**
     * Resetear estado
     */
    fun resetState() {
        _state.value = Resource.Initial
        _error.value = null
        _isLoading.value = false
    }
}

