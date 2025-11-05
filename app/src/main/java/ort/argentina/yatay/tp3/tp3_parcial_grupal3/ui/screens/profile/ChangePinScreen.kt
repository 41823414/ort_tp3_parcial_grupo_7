@file:OptIn(ExperimentalMaterial3Api::class)

package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.TituloBoxPestania
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonSimple

@Composable
fun ChangePinScreen(
    onBack: () -> Unit = {},
    onChangePinSuccess: (String) -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal

    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )

    // Estados para los campos PIN
    var currentPin by remember { mutableStateOf("") }
    var newPin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }

    // Estados para visibilidad
    var currentPinVisible by remember { mutableStateOf(false) }
    var newPinVisible by remember { mutableStateOf(false) }
    var confirmPinVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            FlechaTituloPrincipalCampana(
                titulo = "Change Pin",
                onBack = onBack,
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
            // Contenedor blanco (Surface) ajustado
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
                    // Current Pin Field
                    TituloBoxPestania(
                        label = "Current Pin",
                        value = currentPin,
                        onValueChange = { if (it.length <= 4) currentPin = it },
                        visible = currentPinVisible,
                        onVisibilityToggle = { currentPinVisible = !currentPinVisible },
                        poppinsFontFamily = poppinsFontFamily,
                        boxImage = painterResource(id = R.drawable.puntos)
                    )

                    // New Pin Field
                    TituloBoxPestania(
                        label = "New Pin",
                        value = newPin,
                        onValueChange = { if (it.length <= 4) newPin = it },
                        visible = newPinVisible,
                        onVisibilityToggle = { newPinVisible = !newPinVisible },
                        poppinsFontFamily = poppinsFontFamily,
                        boxImage = painterResource(id = R.drawable.puntos)
                    )

                    // Confirm Pin Field
                    TituloBoxPestania(
                        label = "Confirm Pin",
                        value = confirmPin,
                        onValueChange = { if (it.length <= 4) confirmPin = it },
                        visible = confirmPinVisible,
                        onVisibilityToggle = { confirmPinVisible = !confirmPinVisible },
                        poppinsFontFamily = poppinsFontFamily,
                        boxImage = painterResource(id = R.drawable.puntos)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Change Pin Button - centrado y con ancho reducido
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        BotonSimple(
                            texto = "Change Pin",
                            colorFondo = primaryColor,
                            fillMaxWidth = 1.0f,
                            height = 50,
                            fontSize = 16,
                            onClick = { onChangePinSuccess("Pin Has been Changed successfully") }
                        )
                    }
                }
            }
        }
    }
}
