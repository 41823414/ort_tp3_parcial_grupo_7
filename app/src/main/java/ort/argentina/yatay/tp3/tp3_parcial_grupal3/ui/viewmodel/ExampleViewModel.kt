package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository.ExampleRepository
import javax.inject.Inject

/**
 * Ejemplo de ViewModel con Hilt
 * @HiltViewModel + @Inject permiten la inyección automática del ViewModel
 */
@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val repository: ExampleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            val result = repository.fetchDataFromApi()
            _uiState.value = if (result.isSuccess) {
                UiState.Success(result.getOrNull() ?: "")
            } else {
                UiState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }

    sealed class UiState {
        object Initial : UiState()
        object Loading : UiState()
        data class Success(val data: String) : UiState()
        data class Error(val message: String) : UiState()
    }
}

