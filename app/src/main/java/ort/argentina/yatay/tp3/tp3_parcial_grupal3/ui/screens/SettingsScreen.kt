package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.CustomCard
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.SectionTitle

/**
 * PANTALLA SETTINGS - Pantalla de configuración
 */
@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle(text = "Configuración")

        Text(
            text = "Opciones de configuración de la aplicación",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomCard {
            Text(
                text = "Opciones",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "• Notificaciones")
            Text(text = "• Privacidad")
            Text(text = "• Tema de la app")
            Text(text = "• Idioma")
        }
    }
}

