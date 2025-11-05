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
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonTextoFlecha
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityScreen(
    onNavigateBack: () -> Unit = {},
    onChangePin: () -> Unit = {},
    onFingerprint: () -> Unit = {},
    onTermsAndConditions: () -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal
    val lightBackgroundColor = Color(0xFFE8FFF7) // Verde claro/casi blanco

    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )

    // Altura para que el fondo 0xFFF1FFF3 comience justo después del AppBar
    val topPaddingForSurface = 0.dp

    Scaffold(
        topBar = {
            FlechaTituloPrincipalCampana(
                titulo = "Security",
                onBack = onNavigateBack,
                onNotifications = { /* notificaciones */ },
                primaryColor = primaryColor
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(primaryColor) // Fondo verde arriba, visible hasta la mitad de la imagen
        ) {

            // Contenedor blanco (Surface) ajustado - se extiende hasta abajo cubriendo los botones de navegación
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.BottomCenter)
                    .padding(top = innerPadding.calculateTopPadding() +40.dp),
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
                    // Título Security
                    Text(
                        text = "Security",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppinsFontFamily,
                        color = Color(0xFF093030),
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    // Change Pin
                    BotonTextoFlecha("Change Pin", onChangePin, poppinsFontFamily)

                    // Fingerprint
                    BotonTextoFlecha("Fingerprint", onFingerprint, poppinsFontFamily)

                    // Terms And Conditions
                    BotonTextoFlecha("Terms And Conditions", onTermsAndConditions, poppinsFontFamily)
                }
            }
        }
    }
}

