package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

/**
 * Notifications Screen - Pantalla flotante de notificaciones
 * Esta pantalla se muestra como un diálogo/overlay sobre otras pantallas
 * TODO: Implementar lista de notificaciones
 */
@Composable
fun NotificationsDialog(
    onDismiss: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.notifications_title))
            }
        }
    }
}

