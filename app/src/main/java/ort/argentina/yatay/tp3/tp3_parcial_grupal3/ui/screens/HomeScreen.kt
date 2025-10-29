package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.CustomCard
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.SectionTitle

/**
 * PANTALLA HOME - Pantalla principal de la aplicación
 */
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "Home",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle(text = "Bienvenido a Home")

        Text(
            text = "Esta es la pantalla principal de la aplicación",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomCard {
            Text(
                text = "Funcionalidades",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "• Navegación con Compose")
            Text(text = "• Componentes reutilizables")
            Text(text = "• Bottom Navigation Bar")
            Text(text = "• Material Design 3")
        }
    }
}

