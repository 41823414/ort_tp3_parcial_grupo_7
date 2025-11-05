package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.launch

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * Launch Screen - Pantalla de inicio con dos estados:
 * 1-A: Splash Screen con fondo verde
 * 1-B: Welcome Screen con opciones de Login/Sign Up
 */
@Composable
fun LaunchScreen(
    onNavigateToLogin: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {},
    onNavigateToForgotPassword: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
) {
    var showWelcomeScreen by remember { mutableStateOf(false) }
    
    // Mostrar splash screen por 2 segundos, luego mostrar welcome screen
    LaunchedEffect(Unit) {
        delay(2000)
        showWelcomeScreen = true
    }
    
    if (showWelcomeScreen) {
        WelcomeScreen(
            onNavigateToLogin = onNavigateToLogin,
            onNavigateToSignUp = onNavigateToSignUp,
            onNavigateToForgotPassword = onNavigateToForgotPassword
        )
    } else {
        SplashScreen()
    }
}

/**
 * 1-A: Splash Screen - Pantalla de carga con fondo verde
 */
@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.caribbean_green)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo FinWise
            Image(
                painter = painterResource(R.drawable.finwise_logo),
                contentDescription = "FinWise Logo",
                modifier = Modifier.size(120.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Nombre de la app
            Text(
                text = stringResource(R.string.app_name_finwise),
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = colorResource(R.color.dark_green)
            )
        }
    }
}

/**
 * 1-B: Welcome Screen - Pantalla de bienvenida con opciones de Login/Sign Up
 */
@Composable
fun WelcomeScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.cream)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo FinWise
            Image(
                painter = painterResource(R.drawable.finwise_logo),
                contentDescription = "FinWise Logo",
                modifier = Modifier.size(100.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Título
            Text(
                text = stringResource(R.string.app_name_finwise),
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = colorResource(R.color.dark_green)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Descripción
            Text(
                text = stringResource(R.string.welcome_description),
                fontFamily = poppinsFamily,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Botón Log In (verde con texto blanco)
            Button(
                onClick = onNavigateToLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.caribbean_green)
                )
            ) {
                Text(
                    text = stringResource(R.string.log_in),
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Botón Sign Up (borde verde, fondo transparente)
            OutlinedButton(
                onClick = onNavigateToSignUp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colorResource(R.color.dark_green)
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 2.dp
                )
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = colorResource(R.color.dark_green)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Link Forgot Password
            TextButton(
                onClick = onNavigateToForgotPassword,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.forgot_password),
                    fontFamily = poppinsFamily,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

