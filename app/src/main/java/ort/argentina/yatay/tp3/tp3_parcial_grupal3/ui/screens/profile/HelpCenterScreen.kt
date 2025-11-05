@file:OptIn(ExperimentalMaterial3Api::class)

package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonDesplegable
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonIconTextoFlecha

@Composable
fun HelpCenterScreen(
    onNavigateBack: () -> Unit = {},
    onCustomerService: () -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal

    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )
    
    // Fuente League Spartan
    val leagueSpartanFontFamily = FontFamily(
        Font(R.font.leaguespartan_regular, FontWeight.Normal)
    )

    // Estado para los botones de navegación
    var selectedTab by remember { mutableStateOf("FAQ") }
    
    // Estado para la categoría seleccionada
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    
    // Contenido para todos los desplegables
    val contenidoDesplegable = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

    Scaffold(
        topBar = {
            FlechaTituloPrincipalCampana(
                titulo = "Help & FAQS",
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
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Título "How Can We Help You?"
                    Text(
                        text = "How Can We Help You?",
                        fontSize = 20.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF093030),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    )

                    // Botones de navegación principal (FAQ y Contact Us)
                    Box(
                        modifier = Modifier
                            .width(349.dp)
                            .height(61.dp)
                            .padding(bottom = 16.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFDFF7E2))
                            .padding(horizontal = 8.dp, vertical = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Botón FAQ (seleccionado)
                            Box(
                                modifier = Modifier
                                    .width(153.dp)
                                    .height(51.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(if (selectedTab == "FAQ") primaryColor else Color(0xFFF1FFF3))
                                    .clickable { selectedTab = "FAQ" },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "FAQ",
                                    fontSize = 15.sp,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = if (selectedTab == "FAQ") Color.White else Color(0xFF093030)
                                )
                            }

                            // Botón Contact Us
                            Box(
                                modifier = Modifier
                                    .width(168.dp)
                                    .height(51.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(if (selectedTab == "Contact Us") primaryColor else Color(0xFFF1FFF3))
                                    .clickable { selectedTab = "Contact Us" },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Contact Us",
                                    fontSize = 15.sp,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = if (selectedTab == "Contact Us") Color.White else Color(0xFF093030)
                                )
                            }
                        }
                    }

                    // Botones de categoría (General, Account, Services) envueltos en un box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFDFF7E2))
                            .padding(horizontal = 8.dp, vertical = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            listOf("General", "Account", "Services").forEach { category ->
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(31.dp)
                                        .clickable {
                                            // Toggle: si está seleccionado, desmarcar; si no, marcar
                                            selectedCategory = if (selectedCategory == category) null else category
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Box de selección (solo visible si está seleccionado)
                                    if (selectedCategory == category) {
                                        Box(
                                            modifier = Modifier
                                                .width(95.dp)
                                                .height(31.dp)
                                                .clip(RoundedCornerShape(15.dp))
                                                .background(Color(0xFF00D09E)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = category,
                                                fontSize = 12.sp,
                                                fontFamily = poppinsFontFamily,
                                                fontWeight = FontWeight.Medium,
                                                color = Color.White
                                            )
                                        }
                                    } else {
                                        // Texto sin box cuando no está seleccionado
                                        Text(
                                            text = category,
                                            fontSize = 12.sp,
                                            fontFamily = poppinsFontFamily,
                                            fontWeight = FontWeight.Medium,
                                            color = Color(0xFF093030)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Barra de búsqueda
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(38.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.5.dp, primaryColor, RoundedCornerShape(12.dp))
                            .background(Color(0xFFDFF7E2))
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.search_help),
                                contentDescription = "Search",
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Search",
                                fontSize = 14.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF666666)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Lista de preguntas desplegables
                    if (selectedTab == "FAQ") {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            BotonDesplegable(
                                titulo = "How to use FinWise?",
                                contenido = contenidoDesplegable
                            )
                            BotonDesplegable(
                                titulo = "How much does it cost to use FinWise?",
                                contenido = contenidoDesplegable
                            )
                            BotonDesplegable(
                                titulo = "How to contact support?",
                                contenido = contenidoDesplegable
                            )
                            BotonDesplegable(
                                titulo = "How can I reset my password if I forget it?",
                                contenido = contenidoDesplegable
                            )
                            BotonDesplegable(
                                titulo = "Are there any privacy or data security measures in place?",
                                contenido = contenidoDesplegable
                            )
                            BotonDesplegable(
                                titulo = "Can I customize settings within the application?",
                                contenido = contenidoDesplegable
                            )
                            BotonDesplegable(
                                titulo = "How can I delete my account?",
                                contenido = contenidoDesplegable
                            )
                            BotonDesplegable(
                                titulo = "How do I access my expense history?",
                                contenido = contenidoDesplegable
                            )
                            BotonDesplegable(
                                titulo = "Can I use the app offline?",
                                contenido = contenidoDesplegable
                            )
                        }
                    }
                    
                    // Lista de opciones de contacto
                    if (selectedTab == "Contact Us") {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            BotonIconTextoFlecha(
                                texto = "Customer Service",
                                iconPainter = painterResource(id = R.drawable.asistente_icon),
                                onClick = onCustomerService,
                                fontFamily = poppinsFontFamily,
                                iconWidth = 20.dp,
                                iconHeight = 21.dp,
                                iconBoxWidth = 31.dp,
                                iconBoxHeight = 31.dp,
                                iconBoxColor = Color(0xFF00D09E)
                            )
                            BotonIconTextoFlecha(
                                texto = "Website",
                                iconPainter = painterResource(id = R.drawable.website_icon),
                                onClick = { /* TODO: Implementar acción */ },
                                fontFamily = poppinsFontFamily,
                                iconWidth = 20.dp,
                                iconHeight = 21.dp,
                                iconBoxWidth = 31.dp,
                                iconBoxHeight = 31.dp,
                                iconBoxColor = Color(0xFF00D09E)
                            )
                            BotonIconTextoFlecha(
                                texto = "Facebook",
                                iconPainter = painterResource(id = R.drawable.facebook_icon),
                                onClick = { /* TODO: Implementar acción */ },
                                fontFamily = poppinsFontFamily,
                                iconWidth = 20.dp,
                                iconHeight = 21.dp,
                                iconBoxWidth = 31.dp,
                                iconBoxHeight = 31.dp,
                                iconBoxColor = Color(0xFF00D09E)
                            )
                            BotonIconTextoFlecha(
                                texto = "Whatssapp",
                                iconPainter = painterResource(id = R.drawable.whatsapp_icon),
                                onClick = { /* TODO: Implementar acción */ },
                                fontFamily = poppinsFontFamily,
                                iconWidth = 20.dp,
                                iconHeight = 21.dp,
                                iconBoxWidth = 31.dp,
                                iconBoxHeight = 31.dp,
                                iconBoxColor = Color(0xFF00D09E)
                            )
                            BotonIconTextoFlecha(
                                texto = "Instagram",
                                iconPainter = painterResource(id = R.drawable.instagram_icon),
                                onClick = { /* TODO: Implementar acción */ },
                                fontFamily = poppinsFontFamily,
                                iconWidth = 20.dp,
                                iconHeight = 21.dp,
                                iconBoxWidth = 31.dp,
                                iconBoxHeight = 31.dp,
                                iconBoxColor = Color(0xFF00D09E)
                            )
                        }
                    }
                }
            }
        }
    }
}
