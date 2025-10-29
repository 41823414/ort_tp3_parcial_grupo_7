package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.balance

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

/**
 * Account Balance Screen - Pantalla de balance de cuenta
 * TODO: Implementar visualización de saldos y cuentas
 */
@Composable
fun AccountBalanceScreen(
    onNavigateBack: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.balance_title))
    }
}

