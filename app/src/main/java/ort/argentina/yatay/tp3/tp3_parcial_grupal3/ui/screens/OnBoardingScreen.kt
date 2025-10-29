package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

/**
 * OnBoarding Screen - Pantalla de introducción a la app
 * TODO: Implementar slides de onboarding
 */
@Composable
fun OnBoardingScreen(
    onNavigateToLogin: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.onboarding_title))
    }
}

