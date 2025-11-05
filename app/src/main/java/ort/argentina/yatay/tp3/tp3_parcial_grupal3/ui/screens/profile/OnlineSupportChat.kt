@file:OptIn(ExperimentalMaterial3Api::class)

package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BurbujaTextoChat
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana

@Composable
fun OnlineSupportChatScreen(
    onNavigateBack: () -> Unit = {},
    onHelpCenterClick: () -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal
    val activeTabColor = Color(0xFF00D09E) // Verde para pestaña activa
    val lightGreenColor = Color(0xFFF1FFF3) // Fondo claro
    val messageBackgroundColor = Color(0xFFDFF7E2) // Fondo mensajes asistente

    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )
    
    val poppinsLightFontFamily = FontFamily(
        Font(R.font.poppins_light, FontWeight.Light)
    )

    var selectedTab by remember { mutableStateOf("Support Assistant") }
    var messageText by remember { mutableStateOf("") }

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
            // Contenedor blanco (Surface) ajustado
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.BottomCenter)
                    .padding(top = innerPadding.calculateTopPadding() + 40.dp),
                shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                color = lightGreenColor,
                shadowElevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                ) {
                    // Pestañas
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Pestaña "Support Assistant"
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(if (selectedTab == "Support Assistant") activeTabColor else lightGreenColor)
                                .clickable { selectedTab = "Support Assistant" }
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Support Assistant",
                                fontSize = 15.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = if (selectedTab == "Support Assistant") Color.White else Color(0xFF666666)
                            )
                        }
                        
                        // Pestaña "Help Center"
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(if (selectedTab == "Help Center") activeTabColor else lightGreenColor)
                                .clickable { onHelpCenterClick() }
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Help Center",
                                fontSize = 15.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = if (selectedTab == "Help Center") Color.White else Color(0xFF666666)
                            )
                        }
                    }

                    // Área de mensajes con scroll
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Mensaje del asistente
                        BurbujaTextoChat(
                            texto = "Welcome, I am your virtual assistant.",
                            colorFondo = messageBackgroundColor,
                            alineacionIzquierda = true
                        )
                        
                        // Mensaje del asistente
                        BurbujaTextoChat(
                            texto = "How can I help you today?",
                            colorFondo = messageBackgroundColor,
                            alineacionIzquierda = true
                        )
                        
                        // Timestamp
                        Text(
                            text = "14:00",
                            fontSize = 11.sp,
                            fontFamily = poppinsLightFontFamily,
                            color = Color(0xFF666666),
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Mensaje del usuario
                        BurbujaTextoChat(
                            texto = "Hello! I have a question. How can I record my expenses by date?",
                            colorFondo = activeTabColor,
                            alineacionIzquierda = false
                        )
                        
                        // Timestamp
                        Text(
                            text = "14:01",
                            fontSize = 11.sp,
                            fontFamily = poppinsLightFontFamily,
                            color = Color(0xFF666666),
                            modifier = Modifier.padding(end = 16.dp, top = 4.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.End
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Mensaje del asistente
                        BurbujaTextoChat(
                            texto = "Response to your request: You can register expenses in the top menu of the homepage.",
                            colorFondo = messageBackgroundColor,
                            alineacionIzquierda = true
                        )
                        
                        // Mensaje del asistente
                        BurbujaTextoChat(
                            texto = "Enter the purchase information. including the date, etc.",
                            colorFondo = messageBackgroundColor,
                            alineacionIzquierda = true
                        )
                        
                        // Timestamp
                        Text(
                            text = "14:03",
                            fontSize = 11.sp,
                            fontFamily = poppinsLightFontFamily,
                            color = Color(0xFF666666),
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Mensaje del usuario
                        BurbujaTextoChat(
                            texto = "OK, thanks a lot.",
                            colorFondo = activeTabColor,
                            alineacionIzquierda = false
                        )
                        
                        // Timestamp
                        Text(
                            text = "14:05",
                            fontSize = 11.sp,
                            fontFamily = poppinsLightFontFamily,
                            color = Color(0xFF666666),
                            modifier = Modifier.padding(end = 16.dp, top = 4.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.End
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Mensaje del asistente
                        BurbujaTextoChat(
                            texto = "It was a pleasure to accommodate your request. See you soon!",
                            colorFondo = messageBackgroundColor,
                            alineacionIzquierda = true
                        )
                        
                        // Timestamp
                        Text(
                            text = "14:06 | Chat Ended",
                            fontSize = 11.sp,
                            fontFamily = poppinsLightFontFamily,
                            color = Color(0xFF666666),
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }

                    // Barra de entrada inferior
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                            .padding(bottom = 24.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(activeTabColor)
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Icono de cámara
                            Box(
                                modifier = Modifier
                                    .size(41.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(lightGreenColor)
                                    .clickable { /* TODO: Implementar acción */ },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.chat_camara),
                                    contentDescription = "Camera",
                                    modifier = Modifier.size(28.dp)
                                )
                            }

                            // Campo de texto
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                TextField(
                                    value = messageText,
                                    onValueChange = { messageText = it },
                                    modifier = Modifier.fillMaxSize(),
                                    placeholder = {
                                        Text(
                                            text = "Write Here...",
                                            fontSize = 14.sp,
                                            fontFamily = poppinsFontFamily,
                                            color = Color(0xFF999999)
                                        )
                                    },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent,
                                        disabledContainerColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    textStyle = androidx.compose.ui.text.TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = poppinsFontFamily,
                                        color = Color(0xFF093030)
                                    ),
                                    singleLine = true
                                )
                            }

                            // Icono de micrófono
                            Box(
                                modifier = Modifier
                                    .size(41.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(lightGreenColor)
                                    .clickable { /* TODO: Implementar acción */ },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.chat_microfono),
                                    contentDescription = "Microphone",
                                    modifier = Modifier.size(28.dp)
                                )
                            }

                            // Icono de enviar
                            Box(
                                modifier = Modifier
                                    .size(41.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(lightGreenColor)
                                    .clickable { /* TODO: Implementar acción */ },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.chat_avionpapel),
                                    contentDescription = "Send",
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

