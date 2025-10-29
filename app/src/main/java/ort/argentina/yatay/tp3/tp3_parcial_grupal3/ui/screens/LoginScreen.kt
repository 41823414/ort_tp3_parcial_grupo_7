package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

/**
 * Login Screen - Pantalla de inicio de sesión
 * TODO: Implementar formulario de login con validaciones
 */
@Composable
fun LoginScreen(
    onNavigateToSignUp: () -> Unit = {},
    onNavigateToForgotPassword: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.login_title))
    }
}

