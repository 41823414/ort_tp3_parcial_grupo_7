package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.UserDto
import javax.inject.Inject

/**
 * ApiViewModel - Demuestra el uso de Retrofit + Coil
 * - Retrofit: Llamadas a API REST (JSONPlaceholder)
 * - Coil: Carga de imágenes desde URLs
 */
@HiltViewModel
class ApiViewModel @Inject constructor() : ViewModel() {

    private val _users = MutableStateFlow<List<UserDto>>(emptyList())
    val users: StateFlow<List<UserDto>> = _users.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    sealed class UiState {
        object Initial : UiState()
        object Loading : UiState()
        object Success : UiState()
        object Empty : UiState()
        data class Error(val message: String) : UiState()
    }

    /**
     * Cargar usuarios desde JSONPlaceholder API
     */
    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                // TODO: Implementar llamada a API de JSONPlaceholder
                // Por ahora usamos datos mock
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
                    )
                )

                if (mockUsers.isEmpty()) {
                    _uiState.value = UiState.Empty
                } else {
                    _users.value = mockUsers
                    _uiState.value = UiState.Success
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}
