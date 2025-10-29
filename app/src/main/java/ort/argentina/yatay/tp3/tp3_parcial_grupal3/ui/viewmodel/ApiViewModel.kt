package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.UserDto
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository.ApiRepository
import javax.inject.Inject

/**
 * ViewModel que usa Retrofit para llamadas a API
 * Ejemplo completo de Retrofit + Hilt + ViewModel
 */
@HiltViewModel
class ApiViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    private val _users = MutableStateFlow<List<UserDto>>(emptyList())
    val users: StateFlow<List<UserDto>> = _users.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    /**
     * Cargar usuarios desde la API
     */
    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            apiRepository.getUsers()
                .onSuccess { userList ->
                    _users.value = userList
                    _uiState.value = if (userList.isEmpty()) {
                        UiState.Empty
                    } else {
                        UiState.Success
                    }
                }
                .onFailure { error ->
                    _uiState.value = UiState.Error(error.message ?: "Error desconocido")
                }
        }
    }

    sealed class UiState {
        object Initial : UiState()
        object Loading : UiState()
        object Success : UiState()
        object Empty : UiState()
        data class Error(val message: String) : UiState()
    }
}

