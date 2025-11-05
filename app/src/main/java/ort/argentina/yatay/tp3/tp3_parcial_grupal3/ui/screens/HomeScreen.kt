package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BalanceSection
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.HomeHeader
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.MainContentCard

/**
 * Home Screen - Pantalla principal de la aplicación
 */
@Composable
fun HomeScreen(
    onNavigateToBalance: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategories: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onShowNotifications: () -> Unit = {}
) {
    var selectedPeriod by remember { mutableStateOf("Monthly") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.caribbean_green))
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Header
            HomeHeader(onNotificationClick = onShowNotifications)

            // 2. Balance Section
            BalanceSection()

            // 3. Main Content Card
            MainContentCard(
                selectedPeriod = selectedPeriod,
                onPeriodChange = { selectedPeriod = it },
                onNavigateToTransactions = onNavigateToTransactions
            )
        }
    }
}

