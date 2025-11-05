@file:OptIn(ExperimentalMaterial3Api::class)

package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.TituloBoxPestania
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonSimple

@Composable
fun DeleteAccountScreen(
    onNavigateBack: () -> Unit = {},
    onDeleteAccount: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal

    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )
    
    // Fuente Poppins Light para los textos de advertencia
    val poppinsLightFontFamily = FontFamily(
        Font(R.font.poppins_light, FontWeight.Light)
    )
    
    // Fuente League Spartan para el texto explicativo del diálogo
    val leagueSpartanFontFamily = FontFamily(
        Font(R.font.leaguespartan_regular, FontWeight.Normal)
    )

    // Estado para el campo de contraseña
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    
    // Estado para mostrar el diálogo de confirmación
    var showConfirmDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            FlechaTituloPrincipalCampana(
                titulo = "Delete Account",
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
                    // Pregunta principal
                    Text(
                        text = "Are You Sure You Want To Delete Your Account?",
                        fontSize = 15.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF093030),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    )

                    // Caja de advertencia
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color(0xFFDFF7E2),
                                RoundedCornerShape(12.dp)
                            )
                            .border(
                                1.dp,
                                Color(0xFFA5D6A7),
                                RoundedCornerShape(12.dp)
                            )
                            .padding(16.dp)
                    ) {
                        Column {
                            Text(
                                text = "This action will permanently delete all of your data, and you will not be able to recover it. Please keep the following in mind before proceeding:",
                                fontSize = 14.sp,
                                fontFamily = poppinsLightFontFamily,
                                fontWeight = FontWeight.Light,
                                color = Color(0xFF093030),
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            
                            Text(
                                text = "• All your expenses, income and associated transactions will be eliminated.",
                                fontSize = 14.sp,
                                fontFamily = poppinsLightFontFamily,
                                fontWeight = FontWeight.Light,
                                color = Color(0xFF093030),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            
                            Text(
                                text = "• You will not be able to access your account or any related information.",
                                fontSize = 14.sp,
                                fontFamily = poppinsLightFontFamily,
                                fontWeight = FontWeight.Light,
                                color = Color(0xFF093030),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            
                            Text(
                                text = "• This action cannot be undone.",
                                fontSize = 14.sp,
                                fontFamily = poppinsLightFontFamily,
                                fontWeight = FontWeight.Light,
                                color = Color(0xFF093030)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Segunda pregunta
                    Text(
                        text = "Please Enter Your Password To Confirm Deletion Of Your Account.",
                        fontSize = 15.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF093030),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    // Campo de password sin label
                    TituloBoxPestania(
                        label = null,
                        value = password,
                        onValueChange = { password = it },
                        visible = passwordVisible,
                        onVisibilityToggle = { passwordVisible = !passwordVisible },
                        poppinsFontFamily = poppinsFontFamily,
                        boxImage = painterResource(id = R.drawable.puntos_doble),
                        boxImageWidth = 120.dp,
                        boxImageHeight = 14.dp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Botón "Yes, Delete Account"
                    BotonSimple(
                        texto = "Yes, Delete Account",
                        colorFondo = primaryColor,
                        fillMaxWidth = 0.65f,
                        height = 50,
                        fontSize = 16,
                        onClick = { showConfirmDialog = true }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón "Cancel"
                    BotonSimple(
                        texto = "Cancel",
                        colorFondo = Color(0xFFDFF7E2),
                        fillMaxWidth = 0.65f,
                        height = 50,
                        fontSize = 16,
                        onClick = onCancel
                    )
                }
            }
            
            // Diálogo modal de confirmación
            if (showConfirmDialog) {
                // Fondo semi-transparente (scrim)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable { showConfirmDialog = false },
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
                            // Título "Delete Account"
                            Text(
                                text = "Delete Account",
                                fontSize = 20.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF093030),
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            
                            // Pregunta
                            Text(
                                text = "Are You Sure You Want To Log Out?",
                                fontSize = 16.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF093030),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp)
                            )
                            
                            // Texto explicativo
                            Text(
                                text = "By deleting your account, you agree that you understand the consequences of this action and that you agree to permanently delete your account and all associated data.",
                                fontSize = 17.sp,
                                fontFamily = leagueSpartanFontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF363130),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 24.dp)
                            )
                            
                            // Botón "Yes, Delete Account"
                            BotonSimple(
                                texto = "Yes, Delete Account",
                                colorFondo = primaryColor,
                                fillMaxWidth = 1.0f,
                                height = 45,
                                fontSize = 16,
                                onClick = {
                                    showConfirmDialog = false
                                    onDeleteAccount()
                                }
                            )
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            // Botón "Cancel"
                            BotonSimple(
                                texto = "Cancel",
                                colorFondo = Color(0xFFDFF7E2),
                                fillMaxWidth = 1.0f,
                                height = 45,
                                fontSize = 16,
                                onClick = { showConfirmDialog = false }
                            )
                        }
                    }
                }
            }
        }
    }
}
