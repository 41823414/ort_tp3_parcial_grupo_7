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
import androidx.hilt.navigation.compose.hiltViewModel
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.viewmodel.AuthViewModel

/**
 * 3.0-B: Sign Up Screen - Pantalla de registro de usuario
 */
@Composable
fun SignUpScreen(
    onNavigateToLogin: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val signUpState by authViewModel.signUpState.collectAsState()
    
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    
    // Validaciones básicas
    val isEmailValid = email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.isNotBlank() && password.length >= 6
    val isPasswordMatch = password == confirmPassword
    val isFormValid = isEmailValid && isPasswordValid && isPasswordMatch
    
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
                    .padding(bottom = 40.dp) // + aire inferior
                    .imePadding() // evita que el teclado tape
                    .navigationBarsPadding(), // respeta la barra de gestos
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp)) // Espacio para el texto Create Account
                
                // Campo Full Name
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Full Name",
                        fontFamily = poppinsFamily,
                        fontSize = 13.sp,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        placeholder = {
                            Text(
                                text = "John Doe",
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
                        singleLine = true
                    )
                }
                
                Spacer(modifier = Modifier.height(14.dp))
                
                // Campo Email
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Email",
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
                
                Spacer(modifier = Modifier.height(14.dp))
                
                // Campo Mobile Number
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Mobile Number",
                        fontFamily = poppinsFamily,
                        fontSize = 13.sp,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = mobileNumber,
                        onValueChange = { mobileNumber = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        placeholder = {
                            Text(
                                text = "+ 123 456 789",
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
                            keyboardType = KeyboardType.Phone
                        ),
                        singleLine = true
                    )
                }
                
                Spacer(modifier = Modifier.height(14.dp))
                
                // Campo Date Of Birth
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Date Of Birth",
                        fontFamily = poppinsFamily,
                        fontSize = 13.sp,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = dateOfBirth,
                        onValueChange = { dateOfBirth = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        placeholder = {
                            Text(
                                text = "DD / MM / YYYY",
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
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true
                    )
                }
                
                Spacer(modifier = Modifier.height(14.dp))
                
                // Campo Password
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Password",
                        fontFamily = poppinsFamily,
                        fontSize = 13.sp,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
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
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(
                                onClick = { passwordVisible = !passwordVisible },
                                modifier = Modifier.padding(end = 12.dp)
                            ) {
                                Icon(
                                    painter = painterResource(
                                        if (passwordVisible) R.drawable.eye_open else R.drawable.eye_closed
                                    ),
                                    contentDescription = if (passwordVisible) "Ocultar" else "Mostrar",
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
                        singleLine = true
                    )
                }
                
                Spacer(modifier = Modifier.height(14.dp))
                
                // Campo Confirm Password
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Confirm Password",
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
                        singleLine = true
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Botón Sign Up (píldora centrada)
                Button(
                    onClick = {
                        // Usar email como username o extraer del email
                        val username = email.split("@").firstOrNull() ?: email
                        authViewModel.signUp(username, email, password)
                    },
                    enabled = isFormValid && signUpState !is AuthViewModel.SignUpState.Loading,
                    modifier = Modifier
                        .fillMaxWidth(0.72f)
                        .height(52.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00D49F),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Sign Up",
                        fontFamily = poppinsFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                // Navegar al Home cuando el signUp sea exitoso
                LaunchedEffect(signUpState) {
                    if (signUpState is AuthViewModel.SignUpState.Success) {
                        onNavigateToHome()
                    }
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Términos y condiciones con ClickableText
                Box(
                    modifier = Modifier.fillMaxWidth(0.85f),
                    contentAlignment = Alignment.Center
                ) {
                    val termsText = buildAnnotatedString {
                        append("By continuing, you agree to\n") // ← salto de línea fijo
                        pushStyle(SpanStyle(color = Color(0xFF2F80ED)))
                        pushStringAnnotation("TERMS", "TERMS")
                        append("Terms of Use")
                        pop()
                        pop()
                        append(" and ")
                        pushStyle(SpanStyle(color = Color(0xFF2F80ED)))
                        pushStringAnnotation("PRIVACY", "PRIVACY")
                        append("Privacy Policy")
                        pop()
                        pop()
                        append(".")
                    }
                    
                    ClickableText(
                        text = termsText,
                        onClick = { offset ->
                            termsText.getStringAnnotations(tag = "TERMS", start = 0, end = termsText.length)
                                .firstOrNull { offset in it.start..it.end }
                                ?.let {
                                    // TODO: Navegar a pantalla de Terms of Use
                                } ?: termsText.getStringAnnotations(tag = "PRIVACY", start = 0, end = termsText.length)
                                .firstOrNull { offset in it.start..it.end }
                                ?.let {
                                    // TODO: Navegar a pantalla de Privacy Policy
                                }
                        },
                        style = androidx.compose.ui.text.TextStyle(
                            fontFamily = poppinsFamily,
                            fontSize = 11.sp,
                            color = Color(0xFF6B7280),
                            textAlign = TextAlign.Center,
                            lineHeight = 16.sp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                // Footer con ClickableText (solo "Log In" clickeable)
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    val footerText = buildAnnotatedString {
                        append("Already have an account? ")
                        pushStyle(SpanStyle(color = Color(0xFF2F80ED)))
                        pushStringAnnotation("LOGIN", "LOGIN")
                        append("Log In")
                        pop()
                        pop()
                    }
                    
                    ClickableText(
                        text = footerText,
                        onClick = { offset ->
                            footerText.getStringAnnotations(tag = "LOGIN", start = 0, end = footerText.length)
                                .firstOrNull { offset in it.start..it.end }
                                ?.let {
                                    onNavigateToLogin()
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
                
                // Más respiro después del footer
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
        
        // Capa 3: Texto "Create Account" (arriba de todo) - flotando sobre el borde
        Text(
            text = "Create Account",
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

