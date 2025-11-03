@file:OptIn(ExperimentalMaterial3Api::class)
package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.TituloBox
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.TituloSwitch

@Composable
fun ProfileScreenEdit(
    onNavigateBack: () -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal
    val lightBackgroundColor = Color(0xFFE8FFF7) // Verde claro/casi blanco

    // Estado para Push Notifications (inicia en ON)
    var pushNotificationsEnabled by remember { mutableStateOf(true) }

    // Estado para Turn Dark Theme (inicia en OFF)
    var darkThemeEnabled by remember { mutableStateOf(false) }

    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )

    // Altura de la imagen de perfil
    val profileImageSize = 100.dp
    // Altura para que el fondo 0xFFF1FFF3 se corte exactamente en la mitad de la imagen del perfil
    val topPaddingForSurface = profileImageSize / 2

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Edit My Profile", color = Color(0xFF052224), fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Image(
                            painter = painterResource(id = R.drawable.flecha_atras),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* notificaciones */ }) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            // Icono de notificaciones outlined para coincidir con la imagen
                            Icon(
                                Icons.Outlined.Notifications,
                                contentDescription = "Notifications",
                                tint = Color(0xFF424242), // Gris oscuro para el contorno
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = primaryColor
                )
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
                shape = RoundedCornerShape(
                    topStart = 70.dp,
                    topEnd = 70.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ),
                color = Color(0xFFF1FFF3),
                shadowElevation = 0.dp
            ) {
                // Contenido del Surface con Account Settings
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .padding(top = 150.dp, bottom = 32.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    // Título Account Settings
                    Text(
                        text = "Account Settings",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppinsFontFamily,
                        color = Color(0xFF0E3E3E),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    )

                    // Username Field
                    TituloBox(
                        title = "Username",
                        value = "John Smith",
                        fontFamily = poppinsFontFamily
                    )

                    // Phone Field
                    TituloBox(
                        title = "Phone",
                        value = "+44 555 5555 55",
                        fontFamily = poppinsFontFamily
                    )

                    // Email Address Field
                    TituloBox(
                        title = "Email Address",
                        value = "example@example.com",
                        fontFamily = poppinsFontFamily
                    )

                    // Push Notifications Toggle
                    TituloSwitch(
                        title = "Push Notifications",
                        enabled = pushNotificationsEnabled,
                        onToggle = { pushNotificationsEnabled = it },
                        fontFamily = poppinsFontFamily
                    )

                    // Turn Dark Theme Toggle
                    TituloSwitch(
                        title = "Turn Dark Theme",
                        enabled = darkThemeEnabled,
                        onToggle = { darkThemeEnabled = it },
                        fontFamily = poppinsFontFamily,
                        bottomPadding = 32
                    )

                    // Update Profile Button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(35.dp),
                            shape = RoundedCornerShape(25.dp), // Esquinas muy redondeadas tipo pill
                            colors = ButtonDefaults.buttonColors(
                                containerColor = primaryColor
                            )
                        ) {
                            Text(
                                text = "Update Profile",
                                fontSize = 16.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF0E3E3E) // Color oscuro como en la imagen
                            )
                        }
                    }
                }
            }

            // Información de Perfil (Colocada fuera del Surface blanco para la superposición)
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 90.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagen circular del perfil con ícono de cámara superpuesto
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.eclipse_192),
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(profileImageSize)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    // Ícono de cámara en la esquina inferior derecha
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(25.dp)
                            .offset(x = (-4).dp, y = (-4).dp)
                            .background(primaryColor, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_cam),
                            contentDescription = "Edit profile picture",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }

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
        }
    }
}


