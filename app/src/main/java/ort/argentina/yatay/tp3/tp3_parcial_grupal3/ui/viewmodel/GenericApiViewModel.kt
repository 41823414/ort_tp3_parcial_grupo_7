package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.common.Resource
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.UserDto
import javax.inject.Inject

/**
 * GenericApiViewModel - Demuestra ViewModels genéricos con Resource<T>
 *
 * Implementa:
 * - BaseViewModel pattern con generics
 * - Resource<T> para manejo de estados
 * - Type-safe state management
 */
@HiltViewModel
class GenericApiViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<Resource<List<UserDto>>>(Resource.Initial)
    val state: StateFlow<Resource<List<UserDto>>> = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    /**
     * Cargar usuarios desde datos mock
     * Demuestra el patrón Resource<T> con estados genéricos
     */
    fun loadUsers() {
        viewModelScope.launch {
            _state.value = Resource.Loading()
            _isLoading.value = true

            try {
                // Simular carga desde API (datos mock)
                val mockUsers = listOf(
                    UserDto(
                        id = 1,
                        name = "Leanne Graham",
                        username = "Bret",
                        email = "Sincere@april.biz",
                        phone = "1-770-736-8031 x56442",
                        website = "hildegard.org"
                    ),
                    UserDto(
                        id = 2,
                        name = "Ervin Howell",
                        username = "Antonette",
                        email = "Shanna@melissa.tv",
                        phone = "010-692-6593 x09125",
                        website = "anastasia.net"
                    ),
                    UserDto(
                        id = 3,
                        name = "Clementine Bauch",
                        username = "Samantha",
                        email = "Nathan@yesenia.net",
                        phone = "1-463-123-4447",
                        website = "ramiro.info"
                    ),
                    UserDto(
                        id = 4,
                        name = "Patricia Lebsack",
                        username = "Karianne",
                        email = "Julianne.OConner@kory.com",
                        phone = "493-170-9623 x156",
                        website = "kris.com"
                    )
                )

                if (mockUsers.isEmpty()) {
                    _state.value = Resource.Success(emptyList())
                } else {
                    _state.value = Resource.Success(mockUsers)
                }
                _error.value = null
            } catch (e: Exception) {
                _state.value = Resource.Error(message = e.message ?: "Error desconocido")
                _error.value = e.message ?: "Error al cargar usuarios"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Reintentar carga de usuarios
     */
    fun retry() {
        loadUsers()
    }

    /**
     * Limpiar mensaje de error
     */
    fun clearError() {
        _error.value = null
    }
}

