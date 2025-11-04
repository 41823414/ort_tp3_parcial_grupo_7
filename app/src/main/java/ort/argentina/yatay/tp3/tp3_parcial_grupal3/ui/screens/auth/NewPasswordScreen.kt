package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * 3.2-B: New Password Screen - Pantalla para establecer nueva contraseña
 */
@Composable
fun NewPasswordScreen(
    email: String = "",
    onChangePasswordSuccess: () -> Unit = {}
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    
    // Validaciones
    val isPasswordValid = remember(newPassword) {
        newPassword.length >= 8 && newPassword.any { it.isDigit() }
    }
    
    val passwordsMatch = remember(newPassword, confirmPassword) {
        newPassword.isNotBlank() && newPassword == confirmPassword
    }
    
    val isFormValid = isPasswordValid && passwordsMatch
    
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
                
                // Campo New Password
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "New Password",
                        fontFamily = poppinsFamily,
                        fontSize = 13.sp,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        placeholder = {
                            Text(
                                text = "********",
                                fontFamily = poppinsFamily,
                                fontSize = 14.sp,
                                color = Color(0xFF6B7280).copy(alpha = 0.6f)
                            )
                        },
                        visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(
                                onClick = { newPasswordVisible = !newPasswordVisible },
                                modifier = Modifier.padding(end = 12.dp)
                            ) {
                                Icon(
                                    painter = painterResource(
                                        if (newPasswordVisible) R.drawable.eye_open else R.drawable.eye_closed
                                    ),
                                    contentDescription = if (newPasswordVisible) "Ocultar" else "Mostrar",
                                    tint = Color(0xFF6B7280),
                                    modifier = Modifier.size(24.dp)
                                )
                            }
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
                            keyboardType = KeyboardType.Password
                        ),
                        supportingText = if (newPassword.isNotBlank() && !isPasswordValid) {
                            {
                                Text(
                                    text = "Password must be at least 8 characters with at least 1 number",
                                    fontFamily = poppinsFamily,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        } else null,
                        isError = newPassword.isNotBlank() && !isPasswordValid,
                        singleLine = true
                    )
                }
                
                Spacer(modifier = Modifier.height(14.dp))
                
                // Campo Confirm New Password
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Confirm New Password",
                        fontFamily = poppinsFamily,
                        fontSize = 13.sp,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        placeholder = {
                            Text(
                                text = "********",
                                fontFamily = poppinsFamily,
                                fontSize = 14.sp,
                                color = Color(0xFF6B7280).copy(alpha = 0.6f)
                            )
                        },
                        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(
                                onClick = { confirmPasswordVisible = !confirmPasswordVisible },
                                modifier = Modifier.padding(end = 12.dp)
                            ) {
                                Icon(
                                    painter = painterResource(
                                        if (confirmPasswordVisible) R.drawable.eye_open else R.drawable.eye_closed
                                    ),
                                    contentDescription = if (confirmPasswordVisible) "Ocultar" else "Mostrar",
                                    tint = Color(0xFF6B7280),
                                    modifier = Modifier.size(24.dp)
                                )
                            }
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
                            keyboardType = KeyboardType.Password
                        ),
                        supportingText = if (confirmPassword.isNotBlank() && !passwordsMatch) {
                            {
                                Text(
                                    text = "Passwords do not match",
                                    fontFamily = poppinsFamily,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        } else null,
                        isError = confirmPassword.isNotBlank() && !passwordsMatch,
                        singleLine = true
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Botón Change Password (píldora centrada)
                Button(
                    onClick = onChangePasswordSuccess,
                    modifier = Modifier
                        .fillMaxWidth(0.72f)
                        .height(52.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00D49F),
                        contentColor = Color.White
                    ),
                    enabled = isFormValid
                ) {
                    Text(
                        text = "Change Password",
                        fontFamily = poppinsFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
        
        // Capa 3: Texto "New Password" (arriba de todo) - flotando sobre el borde
        Text(
            text = "New Password",
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

