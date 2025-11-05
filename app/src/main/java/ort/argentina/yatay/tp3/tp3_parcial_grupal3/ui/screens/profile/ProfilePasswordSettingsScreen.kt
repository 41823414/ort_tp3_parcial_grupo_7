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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.TituloBoxPestania
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonSimple

@Composable
fun ProfilePasswordSettingsScreen(
    onNavigateBack: () -> Unit = {},
    onChangePasswordSuccess: (String) -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal

    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )

    // Estados para los campos de contraseña
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Estados para visibilidad
    var currentPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            FlechaTituloPrincipalCampana(
                titulo = "Password Settings",
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
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Current Password Field
                    TituloBoxPestania(
                        label = "Current Password",
                        value = currentPassword,
                        onValueChange = { currentPassword = it },
                        visible = currentPasswordVisible,
                        onVisibilityToggle = { currentPasswordVisible = !currentPasswordVisible },
                        poppinsFontFamily = poppinsFontFamily,
                        boxImage = painterResource(id = R.drawable.puntos_doble),
                        boxImageWidth = 120.dp,
                        boxImageHeight = 14.dp
                    )

                    // New Password Field
                    TituloBoxPestania(
                        label = "New Password",
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        visible = newPasswordVisible,
                        onVisibilityToggle = { newPasswordVisible = !newPasswordVisible },
                        poppinsFontFamily = poppinsFontFamily,
                        boxImage = painterResource(id = R.drawable.puntos_doble),
                        boxImageWidth = 120.dp,
                        boxImageHeight = 14.dp
                    )

                    // Confirm New Password Field
                    TituloBoxPestania(
                        label = "Confirm New Password",
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        visible = confirmPasswordVisible,
                        onVisibilityToggle = { confirmPasswordVisible = !confirmPasswordVisible },
                        poppinsFontFamily = poppinsFontFamily,
                        boxImage = painterResource(id = R.drawable.puntos_doble),
                        boxImageWidth = 120.dp,
                        boxImageHeight = 14.dp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Change Password Button - centrado y con ancho completo
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        BotonSimple(
                            texto = "Change Password",
                            colorFondo = primaryColor,
                            fillMaxWidth = 1.0f,
                            height = 50,
                            fontSize = 16,
                            onClick = { onChangePasswordSuccess("Password Has been Changed successfully") }
                        )
                    }
                }
            }
        }
    }
}
