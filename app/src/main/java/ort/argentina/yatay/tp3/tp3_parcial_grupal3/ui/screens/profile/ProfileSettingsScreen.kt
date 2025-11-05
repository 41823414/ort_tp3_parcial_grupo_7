@file:OptIn(ExperimentalMaterial3Api::class)

package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonIconTextoFlecha
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana

@Composable
fun ProfileSettingsScreen(
    onNavigateBack: () -> Unit = {},
    onNotificationSettings: () -> Unit = {},
    onPasswordSettings: () -> Unit = {},
    onDeleteAccount: () -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal

    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )

    Scaffold(
        topBar = {
            FlechaTituloPrincipalCampana(
                titulo = "Settings",
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
                    // Notification Settings
                    BotonIconTextoFlecha(
                        texto = "Notification Settings",
                        iconPainter = painterResource(id = R.drawable.settings_campana),
                        onClick = onNotificationSettings,
                        fontFamily = poppinsFontFamily,
                        iconWidth = 31.dp,
                        iconHeight = 31.dp
                    )

                    // Password Settings
                    BotonIconTextoFlecha(
                        texto = "Password Settings",
                        iconPainter = painterResource(id = R.drawable.settings_llave),
                        onClick = onPasswordSettings,
                        fontFamily = poppinsFontFamily,
                        iconWidth = 31.dp,
                        iconHeight = 31.dp
                    )

                    // Delete Account
                    BotonIconTextoFlecha(
                        texto = "Delete Account",
                        iconPainter = painterResource(id = R.drawable.settings_persona),
                        onClick = onDeleteAccount,
                        fontFamily = poppinsFontFamily,
                        iconWidth = 31.dp,
                        iconHeight = 31.dp
                    )
                }
            }
        }
    }
}
