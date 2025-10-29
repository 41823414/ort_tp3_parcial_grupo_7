package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.transaction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

/**
 * Transaction Screen - Pantalla de transacciones
 * TODO: Implementar lista de transacciones y formulario de nueva transacción
 */
@Composable
fun TransactionScreen(
    onNavigateBack: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.transaction_title))
    }
}

