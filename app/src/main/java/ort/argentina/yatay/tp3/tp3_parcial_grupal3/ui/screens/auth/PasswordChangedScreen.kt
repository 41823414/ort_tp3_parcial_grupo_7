package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * 3.2-C: Password Changed Screen - Pantalla de éxito al cambiar contraseña
 */
@Composable
fun PasswordChangedScreen(
    onGoLogin: () -> Unit = {}
) {
    // Auto-navegar al Login en 2.5 segundos
    LaunchedEffect(Unit) {
        delay(2500)
        onGoLogin()
    }
    
    // Box principal con fondo verde gradiente
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF00D49F),
                        Color(0xFF00BFA6)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.White.copy(alpha = 0.20f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "Success",
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Password Has Been\nChanged Successfully",
                fontFamily = poppinsFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )
        }
    }
}

