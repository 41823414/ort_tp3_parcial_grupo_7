@file:OptIn(ExperimentalMaterial3Api::class)

package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonSimple
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonSupportAssistant
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana

@Composable
fun OnlineSupportScreen(
    onNavigateBack: () -> Unit = {},
    onStartAnotherChat: () -> Unit = {},
    onSupportAssistantClick: () -> Unit = {}
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
                titulo = "Online Support",
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
                // Contenido principal con scroll
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 32.dp)
                        .padding(top = 50.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    // Sección "Active Chats"
                    Text(
                        text = "Active Chats",
                        fontSize = 18.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF093030),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    BotonSupportAssistant(
                        titulo = "Support Assistant",
                        contenido = "Hello! I'm here to assist you",
                        fecha = "2 Min Ago",
                        onClick = onSupportAssistantClick,
                        iconPainter = painterResource(id = R.drawable.asistente_icon)
                    )
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Sección "Ended Chats"
                    Text(
                        text = "Ended Chats",
                        fontSize = 18.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF093030),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        BotonSupportAssistant(
                            titulo = "Help Center",
                            contenido = "Your account is ready to use...",
                            fecha = "Feb 08 -2024",
                            onClick = { /* TODO: Implementar acción */ },
                            iconPainter = painterResource(id = R.drawable.asistente_icon)
                        )
                        
                        BotonSupportAssistant(
                            titulo = "Support Assistant",
                            contenido = "Hello! I'm here to assist you",
                            fecha = "Dic 24 -2023",
                            onClick = { /* TODO: Implementar acción */ },
                            iconPainter = painterResource(id = R.drawable.asistente_icon)
                        )
                        
                        BotonSupportAssistant(
                            titulo = "Support Assistant",
                            contenido = "Hello! I'm here to assist you",
                            fecha = "Sep 10 -2023",
                            onClick = { /* TODO: Implementar acción */ },
                            iconPainter = painterResource(id = R.drawable.asistente_icon)
                        )
                        
                        BotonSupportAssistant(
                            titulo = "Help Center",
                            contenido = "Hi, how are you today?",
                            fecha = "June 12 -2023",
                            onClick = { /* TODO: Implementar acción */ },
                            iconPainter = painterResource(id = R.drawable.asistente_icon)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Botón "Start Another Chat"
                    BotonSimple(
                        texto = "Start Another Chat",
                        colorFondo = primaryColor,
                        fillMaxWidth = 1.0f,
                        height = 50,
                        fontSize = 16,
                        onClick = onStartAnotherChat
                    )
                }
            }
        }
    }
}
