package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.viewmodel.ExampleViewModel

/**
 * Ejemplo de Composable usando Hilt para inyectar ViewModel
 * hiltViewModel() es una función que automáticamente obtiene el ViewModel
 * con todas sus dependencias inyectadas por Dagger/Hilt
 */
@Composable
fun ExampleScreen(
    viewModel: ExampleViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ejemplo de Dagger/Hilt",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        when (uiState) {
            is ExampleViewModel.UiState.Initial -> {
                Button(onClick = { viewModel.loadData() }) {
                    Text("Cargar Datos")
                }
            }
            is ExampleViewModel.UiState.Loading -> {
                CircularProgressIndicator()
            }
            is ExampleViewModel.UiState.Success -> {
                Text(
                    text = (uiState as ExampleViewModel.UiState.Success).data,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(onClick = { viewModel.loadData() }) {
                    Text("Recargar")
                }
            }
            is ExampleViewModel.UiState.Error -> {
                Text(
                    text = "Error: ${(uiState as ExampleViewModel.UiState.Error).message}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(onClick = { viewModel.loadData() }) {
                    Text("Reintentar")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "✓ ViewModel inyectado con @HiltViewModel\n" +
                   "✓ Repository inyectado automáticamente\n" +
                   "✓ Scopes y ciclos de vida manejados por Hilt",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

