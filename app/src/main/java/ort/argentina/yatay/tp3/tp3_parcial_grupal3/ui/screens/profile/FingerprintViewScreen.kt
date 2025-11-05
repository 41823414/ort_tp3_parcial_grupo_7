@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonSimple
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana

@Composable
fun FingerprintViewScreen(
    onBack: () -> Unit = {},
    onDelete: () -> Unit = {}
) {
    val primaryColor = Color(0xFF00D09E) // Verde principal (o Color(0xFF00C896) si prefieres el del código base)
    
    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )

    Scaffold(
        topBar = {
            FlechaTituloPrincipalCampana(
                titulo = "John Fingerprint",
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
            // Contenedor verde claro (Surface) con bordes redondeados arriba
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .padding(top = 100.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Círculo verde grande con el icono de huella
                    Box(
                        modifier = Modifier.size(180.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(primaryColor, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.fingerprint_green),
                                contentDescription = "Fingerprint",
                                modifier = Modifier
                                    .fillMaxSize(0.7f)
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(50.dp))
                    
                    // Botón "John Fingerprint" (fondo claro)
                    BotonSimple(
                        texto = "John Fingerprint",
                        colorFondo = Color(0xFFDFF7E2),
                        fillMaxWidth = 1.0f,
                        height = 41,
                        fontSize = 16,
                        onClick = { /* acción del botón */ }
                    )
                    
                    Spacer(modifier = Modifier.height(100.dp))
                    
                    // Botón "Delete" (fondo verde)
                    BotonSimple(
                        texto = "Delete",
                        colorFondo = primaryColor,
                        fillMaxWidth = 0.5f,
                        height = 45,
                        fontSize = 16,
                        onClick = onDelete
                    )
                }
            }
        }
    }
}