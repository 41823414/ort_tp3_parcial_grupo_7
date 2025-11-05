package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.zIndex
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * 3.1-A: Forgot Password Screen - Pantalla de recuperación de contraseña
 */
@Composable
fun ForgotPasswordScreen(
    onNextStep: (String) -> Unit = {},
    onNavigateToSignUp: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    
    // Validación básica de email
    val isEmailValid = remember(email) {
        email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    // Box principal con fondo #F6FFF8
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6FFF8))
    ) {
        // Capa 1: Header verde (abajo) - recto sin curvas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF00D49F),
                            Color(0xFF00BFA6)
                        )
                    )
                )
        )
        
        // Capa 2: Surface del formulario (encima del header)
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp),
            color = Color(0xFFF6FFF8)
        ) {
            // Contenido scrollable del formulario
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 40.dp)
                    .imePadding()
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp)) // Espacio para el título
                
                // Subtítulo
                Text(
                    text = "Reset Password?",
                    fontFamily = poppinsFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Párrafo introductorio
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                    fontFamily = poppinsFamily,
                    fontSize = 11.sp,
                    color = Color(0xFF6B7280),
                    lineHeight = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Campo Email
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Enter Email Address",
                        fontFamily = poppinsFamily,
                        fontSize = 13.sp,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        placeholder = {
                            Text(
                                text = "example@example.com",
                                fontFamily = poppinsFamily,
                                fontSize = 14.sp,
                                color = Color(0xFF6B7280).copy(alpha = 0.6f)
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFEFF7F3),
                            unfocusedContainerColor = Color(0xFFEFF7F3),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            focusedLabelColor = Color.Black
                        ),
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = poppinsFamily,
                            fontSize = 14.sp
                        ),
                        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        singleLine = true
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Botón Next Step (píldora centrada)
                Button(
                    onClick = { onNextStep(email) },
                    modifier = Modifier
                        .fillMaxWidth(0.72f)
                        .height(52.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00D49F),
                        contentColor = Color.White
                    ),
                    enabled = isEmailValid
                ) {
                    Text(
                        text = "Next Step",
                        fontFamily = poppinsFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Botón Sign Up (secundario)
                Button(
                    onClick = onNavigateToSignUp,
                    modifier = Modifier
                        .fillMaxWidth(0.72f)
                        .height(52.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEEF3EF),
                        contentColor = Color(0xFF0E6F5A)
                    )
                ) {
                    Text(
                        text = "Sign Up",
                        fontFamily = poppinsFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Label "or sign up with"
                Text(
                    text = "or sign up with",
                    fontFamily = poppinsFamily,
                    fontSize = 12.sp,
                    color = Color(0xFF6B7280).copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(10.dp))
                
                // Social buttons (Facebook a la izquierda, Google a la derecha)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Botón Facebook
                    Box(
                        modifier = Modifier
                            .size(33.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color(0xFFE1E4E8), CircleShape)
                            .clickable(onClick = { /* TODO */ }),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.facebook),
                            contentDescription = "Facebook",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Unspecified
                        )
                    }
                    
                    // Botón Google
                    Box(
                        modifier = Modifier
                            .size(33.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color(0xFFE1E4E8), CircleShape)
                            .clickable(onClick = { /* TODO */ }),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.google),
                            contentDescription = "Google",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Footer con ClickableText (solo "Sign Up" clickeable)
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    val footerText = buildAnnotatedString {
                        append("Don't have an account? ")
                        pushStyle(SpanStyle(color = Color(0xFF2F80ED)))
                        pushStringAnnotation("SIGNUP", "SIGNUP")
                        append("Sign Up")
                        pop()
                        pop()
                    }
                    
                    ClickableText(
                        text = footerText,
                        onClick = { offset ->
                            footerText.getStringAnnotations(tag = "SIGNUP", start = 0, end = footerText.length)
                                .firstOrNull { offset in it.start..it.end }
                                ?.let {
                                    onNavigateToSignUp()
                                }
                        },
                        style = androidx.compose.ui.text.TextStyle(
                            fontFamily = poppinsFamily,
                            fontSize = 12.sp,
                            color = Color(0xFF6B7280),
                            textAlign = TextAlign.Center,
                            lineHeight = 16.sp
                        )
                    )
                }
                
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
        
        // Capa 3: Texto "Forgot Password" (arriba de todo) - flotando sobre el borde
        Text(
            text = "Forgot Password",
            fontFamily = poppinsFamily,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
                .zIndex(2f)
        )
    }
}

