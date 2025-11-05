@file:OptIn(ExperimentalMaterial3Api::class)

package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.TituloSwitch

@Composable
fun ProfileNotificationSettingsScreen(
    onNavigateBack: () -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal

    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )

    // Estados para cada toggle switch
    var generalNotification by remember { mutableStateOf(true) }
    var sound by remember { mutableStateOf(true) }
    var soundCall by remember { mutableStateOf(true) }
    var vibrate by remember { mutableStateOf(true) }
    var transactionUpdate by remember { mutableStateOf(false) }
    var expenseReminder by remember { mutableStateOf(false) }
    var budgetNotifications by remember { mutableStateOf(false) }
    var lowBalanceAlerts by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            FlechaTituloPrincipalCampana(
                titulo = "Notification Settings",
                onBack = onNavigateBack,
                onNotifications = { /* notificaciones */ },
                primaryColor = primaryColor
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(primaryColor)
        ) {
            // Contenedor blanco (Surface) ajustado - se extiende hasta abajo cubriendo los botones de navegación
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.BottomCenter)
                    .padding(top = innerPadding.calculateTopPadding() + 40.dp),
                shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                color = Color(0xFFF1FFF3),
                shadowElevation = 0.dp
            ) {
                // Contenido principal
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .padding(top = 50.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    // General Notification
                    TituloSwitch(
                        title = "General Notification",
                        enabled = generalNotification,
                        onToggle = { generalNotification = it },
                        fontFamily = poppinsFontFamily
                    )

                    // Sound
                    TituloSwitch(
                        title = "Sound",
                        enabled = sound,
                        onToggle = { sound = it },
                        fontFamily = poppinsFontFamily
                    )

                    // Sound Call
                    TituloSwitch(
                        title = "Sound Call",
                        enabled = soundCall,
                        onToggle = { soundCall = it },
                        fontFamily = poppinsFontFamily
                    )

                    // Vibrate
                    TituloSwitch(
                        title = "Vibrate",
                        enabled = vibrate,
                        onToggle = { vibrate = it },
                        fontFamily = poppinsFontFamily
                    )

                    // Transaction Update
                    TituloSwitch(
                        title = "Transaction Update",
                        enabled = transactionUpdate,
                        onToggle = { transactionUpdate = it },
                        fontFamily = poppinsFontFamily
                    )

                    // Expense Reminder
                    TituloSwitch(
                        title = "Expense Reminder",
                        enabled = expenseReminder,
                        onToggle = { expenseReminder = it },
                        fontFamily = poppinsFontFamily
                    )

                    // Budget Notifications
                    TituloSwitch(
                        title = "Budget Notifications",
                        enabled = budgetNotifications,
                        onToggle = { budgetNotifications = it },
                        fontFamily = poppinsFontFamily
                    )

                    // Low Balance Alerts
                    TituloSwitch(
                        title = "Low Balance Alerts",
                        enabled = lowBalanceAlerts,
                        onToggle = { lowBalanceAlerts = it },
                        fontFamily = poppinsFontFamily
                    )
                }
            }
        }
    }
}
