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
fun FingerprintAddScreen(
    onBack: () -> Unit = {},
    onUseTouchId: () -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal
    
    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )
    
    // Fuente League Spartan para el texto descriptivo
    val leagueSpartanFontFamily = FontFamily(
        Font(R.font.leaguespartan_regular, FontWeight.Normal)
    )

    Scaffold(
        topBar = {
            FlechaTituloPrincipalCampana(
                titulo = "Add Fingerprint",
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
                    
                    // Título "Use Fingerprint To Access"
                    Text(
                        text = "Use Fingerprint To Access",
                        fontSize = 20.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF093030),
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    // Texto descriptivo
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
                        fontSize = 14.sp,
                        fontFamily = leagueSpartanFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF093030),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    Spacer(modifier = Modifier.height(60.dp))
                    
                    // Botón "Use Touch Id"
                    BotonSimple(
                        texto = "Use Touch Id",
                        colorFondo = Color(0xFFD9F7E8),
                        fillMaxWidth = 1.0f,
                        height = 45,
                        fontSize = 16,
                        onClick = onUseTouchId
                    )
                }
            }
        }
    }
}
