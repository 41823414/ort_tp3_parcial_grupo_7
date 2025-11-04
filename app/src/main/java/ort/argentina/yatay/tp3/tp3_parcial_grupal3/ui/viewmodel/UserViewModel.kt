package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.User
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository.UserRepository
import javax.inject.Inject

/**
 * ViewModel que usa modelos de dominio (Clean Architecture)
 * Usa UserRepository de la capa de dominio, no entidades de datos directamente
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    /**
     * Cargar usuarios desde la base de datos
     * Flow observa cambios automáticamente
     * Usa modelos de dominio (User) en lugar de entidades (UserEntity)
     */
    private fun loadUsers() {
        viewModelScope.launch {
            userRepository.getAllUsers()
                .catch { e ->
                    _uiState.value = UiState.Error(e.message ?: "Error desconocido")
                }
                .collect { userList ->
                    _users.value = userList
                    _uiState.value = if (userList.isEmpty()) {
                        UiState.Empty
                    } else {
                        UiState.Success
                    }
                }
        }
    }

    /**
     * Agregar un nuevo usuario
     * Recibe parámetros y crea un modelo de dominio
     */
    fun addUser(name: String, email: String, age: Int) {
        viewModelScope.launch {
            try {
                val user = User(
                    name = name,
                    email = email,
                    age = age
                )
                userRepository.insertUser(user)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error al agregar usuario")
            }
        }
    }

    /**
     * Actualizar un usuario existente
     * Recibe un modelo de dominio
     */
    fun updateUser(user: User) {
        viewModelScope.launch {
            try {
                userRepository.updateUser(user)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error al actualizar usuario")
            }
        }
    }

    /**
     * Eliminar un usuario
     * Recibe un modelo de dominio
     */
    fun deleteUser(user: User) {
        viewModelScope.launch {
            try {
                userRepository.deleteUser(user)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error al eliminar usuario")
            }
        }
    }

    /**
     * Agregar usuarios de ejemplo
     */
    fun addSampleUsers() {
        viewModelScope.launch {
            val sampleUsers = listOf(
                User(name = "Juan Pérez", email = "juan@example.com", age = 25),
                User(name = "María García", email = "maria@example.com", age = 30),
                User(name = "Carlos López", email = "carlos@example.com", age = 28),
                User(name = "Ana Martínez", email = "ana@example.com", age = 22)
            )
            userRepository.insertUsers(sampleUsers)
        }
    }

    /**
     * Eliminar todos los usuarios
     */
    fun deleteAllUsers() {
        viewModelScope.launch {
            try {
                userRepository.deleteAllUsers()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error al eliminar usuarios")
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        object Success : UiState()
        object Empty : UiState()
        data class Error(val message: String) : UiState()
    }
}
