package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.CustomCard
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.SectionTitle

/**
 * PANTALLA PROFILE - Pantalla de perfil de usuario
 */
@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle(text = "Perfil de Usuario")

        Text(
            text = "Aquí se mostrará la información del perfil",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomCard {
            Text(
                text = "Información",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Nombre: Usuario Demo")
            Text(text = "Email: usuario@ejemplo.com")
            Text(text = "Rol: Desarrollador")
        }
    }
}

