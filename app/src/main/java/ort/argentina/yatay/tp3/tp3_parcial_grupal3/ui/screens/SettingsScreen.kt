package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
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
            contentDescription = stringResource(R.string.settings_title),
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle(text = stringResource(R.string.settings_title))

        Text(
            text = stringResource(R.string.settings_description),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomCard {
            Text(
                text = stringResource(R.string.settings_options),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "• ${stringResource(R.string.settings_notifications)}")
            Text(text = "• ${stringResource(R.string.settings_privacy)}")
            Text(text = "• ${stringResource(R.string.settings_theme)}")
            Text(text = "• ${stringResource(R.string.settings_language)}")
        }
    }
}

