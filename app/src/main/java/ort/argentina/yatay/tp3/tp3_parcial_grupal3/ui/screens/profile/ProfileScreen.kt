@file:OptIn(ExperimentalMaterial3Api::class)

package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonIconoTitulo
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonSimple

/**
 * Profile Screen - Pantalla de perfil de usuario
 * TODO: Implementar visualización y edición de perfil
 */
@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit = {},
    //onLogout: () -> Unit = {},
    onEditProfile: () -> Unit = {},
    onSecurity: () -> Unit = {},
    onSetting: () -> Unit = {},
    onHelp: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal
    val lightBackgroundColor = Color(0xFFE8FFF7) // Verde claro/casi blanco

    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )
    
    // Fuente League Spartan para el texto del diálogo
    val leagueSpartanFontFamily = FontFamily(
        Font(R.font.leaguespartan_regular, FontWeight.Normal)
    )
    
    // Estado para mostrar el diálogo de logout
    var showLogoutDialog by remember { mutableStateOf(false) }

    // Altura de la imagen de perfil
    val profileImageSize = 100.dp
    // Altura para que el fondo 0xFFF1FFF3 se corte exactamente en la mitad de la imagen del perfil
    val topPaddingForSurface = profileImageSize / 2

    Scaffold(
        topBar = {
            FlechaTituloPrincipalCampana(
                titulo = "Profile",
                onBack = { /* volver */ },
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
                    // **Punto clave 1: Ajuste de padding para que comience en la mitad de la imagen**
                    // Se extiende hasta abajo sin padding, cubriendo los botones de navegación
                    .padding(top = topPaddingForSurface + innerPadding.calculateTopPadding()),
                shape = RoundedCornerShape(topStart = 70.dp, topEnd = 70.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                color = Color(0xFFF1FFF3),
                shadowElevation = 0.dp // Eliminamos la sombra del contenedor principal
            ) {
                // Contenido de la tarjeta blanca
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        // Posicionar el primer item a 350dp desde arriba y 38dp desde la izquierda
                        .padding(top = 150.dp, start = 38.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Opciones de menú con el nuevo estilo de fila simple
                    BotonIconoTitulo(
                        icon = painterResource(id = R.drawable.icon_profile),
                        title = "Edit Profile",
                        onClick = onEditProfile,
                        fontFamily = poppinsFontFamily
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BotonIconoTitulo(
                        icon = painterResource(id = R.drawable.icon_security),
                        title = "Security",
                        onClick = onSecurity,
                        fontFamily = poppinsFontFamily
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BotonIconoTitulo(
                        icon = painterResource(id = R.drawable.icon_setting),
                        title = "Setting",
                        onClick = onSetting,
                        fontFamily = poppinsFontFamily
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BotonIconoTitulo(
                        icon = painterResource(id = R.drawable.icon_help),
                        title = "Help",
                        onClick = onHelp,
                        fontFamily = poppinsFontFamily
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BotonIconoTitulo(
                        icon = painterResource(id = R.drawable.icon_logout),
                        title = "Logout",
                        onClick = { showLogoutDialog = true },
                        fontFamily = poppinsFontFamily
                    )
                }
            }

            // Información de Perfil (Colocada fuera del Surface blanco para la superposición)
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 90.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagen circular del perfil
                Image(
                    painter = painterResource(id = R.drawable.eclipse_192),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(profileImageSize)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "John Smith",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFontFamily,
                    color = Color(0xFF0E3E3E)
                )

                Text(
                    text = "ID: 25030024",
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            // Diálogo modal de confirmación de logout
            if (showLogoutDialog) {
                // Fondo semi-transparente (scrim)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable { showLogoutDialog = false },
                    contentAlignment = Alignment.Center
                ) {
                    // Diálogo blanco
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .padding(horizontal = 24.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFFFFF)
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Título "End Session"
                            Text(
                                text = "End Session",
                                fontSize = 20.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF093030),
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            
                            // Pregunta
                            Text(
                                text = "Are you sure you want to log out?",
                                fontSize = 17.sp,
                                fontFamily = leagueSpartanFontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF093030),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 24.dp)
                            )
                            
                            // Botón "Yes, End Session"
                            BotonSimple(
                                texto = "Yes, End Session",
                                colorFondo = primaryColor,
                                fillMaxWidth = 0.8f,
                                height = 45,
                                fontSize = 16,
                                onClick = {
                                    showLogoutDialog = false
                                    onLogout()
                                }
                            )
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            // Botón "Cancel"
                            BotonSimple(
                                texto = "Cancel",
                                colorFondo = Color(0xFFDFF7E2),
                                fillMaxWidth = 0.8f,
                                height = 45,
                                fontSize = 16,
                                onClick = { showLogoutDialog = false }
                            )
                        }
                    }
                }
            }
        }
    }
}
