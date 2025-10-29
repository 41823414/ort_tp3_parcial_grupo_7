package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.base.BaseViewModel
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.UserDto
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository.ApiRepository
import javax.inject.Inject

/**
 * EJEMPLO: ViewModel usando BaseViewModel genérico
 *
 * GenericApiViewModel hereda de BaseViewModel<List<UserDto>>
 * Esto proporciona automáticamente:
 * - state: StateFlow<Resource<List<UserDto>>>
 * - isLoading: StateFlow<Boolean>
 * - error: StateFlow<String?>
 * - Funciones helper para manejo de estados
 */
@HiltViewModel
class GenericApiViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel<List<UserDto>>() {

    /**
     * Cargar usuarios usando executeWithResource
     * Maneja automáticamente Loading -> Success/Error
     */
    fun loadUsers() {
        executeWithResource {
            val result = apiRepository.getUsers()
            result.getOrThrow() // Lanzar excepción si falla
        }
    }

    /**
     * Cargar usuario específico (ejemplo de transformación)
     */
    fun loadUserById(userId: Int) {
        launchDataLoad {
            val result = apiRepository.getUserById(userId)
            if (result.isSuccess) {
                // Transformar un User a List<User> para el estado
                _state.value = ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.common.Resource.Success(
                    listOf(result.getOrNull()!!)
                )
            } else {
                handleError(result.exceptionOrNull() ?: Exception("Error desconocido"))
            }
        }
    }

    /**
     * Simular carga con delay (para testing)
     */
    fun loadUsersWithDelay() {
        launchDataLoad {
            delay(2000) // Simular latencia de red
            loadUsers()
        }
    }

    /**
     * Reintentar última operación
     */
    fun retry() {
        clearError()
        loadUsers()
    }
}

