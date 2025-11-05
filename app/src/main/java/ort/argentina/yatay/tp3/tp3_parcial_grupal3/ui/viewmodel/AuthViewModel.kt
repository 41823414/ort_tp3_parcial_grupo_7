package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.model.User
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state.asStateFlow()
    
    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState.asStateFlow()

    fun login(username: String, password: String) {
        Log.d("AuthViewModel", "=== LOGIN INICIADO ===")
        Log.d("AuthViewModel", "Username: $username, Password length: ${password.length}")
        viewModelScope.launch {
            _state.value = LoginState.Loading
            Log.d("AuthViewModel", "Estado cambiado a Loading")
            val result = authRepository.login(username, password)
            Log.d("AuthViewModel", "Resultado recibido: ${result.isSuccess}")
            _state.value = result.fold(
                onSuccess = { 
                    Log.d("AuthViewModel", "Login SUCCESS: user=${it.name}, id=${it.id}")
                    LoginState.Success(it) 
                },
                onFailure = { 
                    Log.e("AuthViewModel", "Login ERROR: ${it.message}", it)
                    LoginState.Error(it.message ?: "Error de login") 
                }
            )
        }
    }

    fun signUp(username: String, email: String, password: String) {
        Log.d("AuthViewModel", "=== SIGNUP INICIADO ===")
        Log.d("AuthViewModel", "Username: $username, Email: $email, Password length: ${password.length}")
        viewModelScope.launch {
            _signUpState.value = SignUpState.Loading
            Log.d("AuthViewModel", "SignUp estado cambiado a Loading")
            val result = authRepository.signUp(username, email, password)
            Log.d("AuthViewModel", "SignUp resultado recibido: ${result.isSuccess}")
            _signUpState.value = result.fold(
                onSuccess = { 
                    Log.d("AuthViewModel", "SignUp SUCCESS: user=${it.name}, id=${it.id}, email=${it.email}")
                    SignUpState.Success(it) 
                },
                onFailure = { 
                    Log.e("AuthViewModel", "SignUp ERROR: ${it.message}", it)
                    SignUpState.Error(it.message ?: "Error de registro") 
                }
            )
        }
    }

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        data class Success(val user: User) : LoginState()
        data class Error(val message: String) : LoginState()
    }
    
    sealed class SignUpState {
        object Idle : SignUpState()
        object Loading : SignUpState()
        data class Success(val user: User) : SignUpState()
        data class Error(val message: String) : SignUpState()
    }
}


