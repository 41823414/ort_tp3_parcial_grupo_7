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
    
    // Funciones de validación
    fun isValidFullName(name: String): Boolean {
        val trimmed = name.trim()
        if (trimmed.isEmpty()) return false
        // Mínimo 2 palabras, solo letras y espacios
        val words = trimmed.split("\\s+".toRegex()).filter { it.isNotBlank() }
        if (words.size < 2) return false
        // Solo letras, espacios y acentos
        return trimmed.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$"))
    }
    
    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    fun isValidMobileNumber(phone: String): Boolean {
        if (phone.isBlank()) return false
        // Acepta: +XX XXXX XXXX, (XX) XXXX-XXXX, XXXX-XXXX, números con espacios/guión
        val cleaned = phone.replace(Regex("[\\s\\-\\(\\)]"), "")
        // Mínimo 10 dígitos, máximo 15 (incluye código de país)
        return cleaned.matches(Regex("^\\+?[0-9]{10,15}$"))
    }
    
    fun isValidDateOfBirth(date: String): Boolean {
        if (date.isBlank()) return false
        // Formato DD/MM/YYYY
        val pattern = Regex("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$")
        if (!date.matches(pattern)) return false
        
        // Validar que la fecha sea real
        val parts = date.split("/")
        val day = parts[0].toIntOrNull() ?: return false
        val month = parts[1].toIntOrNull() ?: return false
        val year = parts[2].toIntOrNull() ?: return false
        
        // Validar año razonable (1900-2024)
        if (year < 1900 || year > 2024) return false
        
        // Validar días por mes
        val daysInMonth = when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
            else -> return false
        }
        
        return day in 1..daysInMonth
    }
    
    fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        // Debe tener al menos: 1 mayúscula, 1 minúscula, 1 número
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        return hasUpperCase && hasLowerCase && hasDigit
    }
    
    // Validaciones por campo
    val isFullNameValid = remember(fullName) { fullName.isBlank() || isValidFullName(fullName) }
    val fullNameError = remember(fullName) {
        if (fullName.isBlank()) null
        else if (!isValidFullName(fullName)) "Debe contener al menos 2 palabras (solo letras)"
        else null
    }
    
    val isEmailValid = remember(email) { email.isBlank() || isValidEmail(email) }
    val emailError = remember(email) {
        if (email.isBlank()) null
        else if (!isValidEmail(email)) "Email inválido"
        else null
    }
    
    val isMobileNumberValid = remember(mobileNumber) { mobileNumber.isBlank() || isValidMobileNumber(mobileNumber) }
    val mobileNumberError = remember(mobileNumber) {
        if (mobileNumber.isBlank()) null
        else if (!isValidMobileNumber(mobileNumber)) "Formato inválido (ej: +54 11 1234-5678)"
        else null
    }
    
    val isDateOfBirthValid = remember(dateOfBirth) { dateOfBirth.isBlank() || isValidDateOfBirth(dateOfBirth) }
    val dateOfBirthError = remember(dateOfBirth) {
        if (dateOfBirth.isBlank()) null
        else if (!isValidDateOfBirth(dateOfBirth)) "Formato inválido (DD/MM/YYYY)"
        else null
    }
    
    val isPasswordValid = remember(password) { password.isBlank() || isValidPassword(password) }
    val passwordError = remember(password) {
        if (password.isBlank()) null
        else if (password.length < 8) "Mínimo 8 caracteres"
        else if (!isValidPassword(password)) "Debe tener mayúscula, minúscula y número"
        else null
    }
    
    val isPasswordMatch = remember(password, confirmPassword) {
        confirmPassword.isBlank() || password == confirmPassword
    }
    val confirmPasswordError = remember(password, confirmPassword) {
        if (confirmPassword.isBlank()) null
        else if (password != confirmPassword) "Las contraseñas no coinciden"
        else null
    }
    
    // Validación general del formulario
    val isFormValid = isFullNameValid && isEmailValid && isMobileNumberValid && 
                     isDateOfBirthValid && isPasswordValid && isPasswordMatch &&
                     fullName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && 
                     confirmPassword.isNotBlank()
    
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
                    if (fullNameError != null) {
                        Text(
                            text = fullNameError,
                            fontFamily = poppinsFamily,
                            fontSize = 11.sp,
                            color = Color(0xFFEF4444),
                            modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                        )
                    }
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
                    if (emailError != null) {
                        Text(
                            text = emailError,
                            fontFamily = poppinsFamily,
                            fontSize = 11.sp,
                            color = Color(0xFFEF4444),
                            modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                        )
                    }
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
                    if (mobileNumberError != null) {
                        Text(
                            text = mobileNumberError,
                            fontFamily = poppinsFamily,
                            fontSize = 11.sp,
                            color = Color(0xFFEF4444),
                            modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                        )
                    }
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
                        onValueChange = { newValue ->
                            // Formatear automáticamente DD/MM/YYYY
                            val filtered = newValue.filter { it.isDigit() }
                            dateOfBirth = when {
                                filtered.isEmpty() -> ""
                                filtered.length <= 2 -> filtered
                                filtered.length <= 4 -> {
                                    val day = filtered.substring(0, 2)
                                    val month = filtered.substring(2)
                                    "$day/$month"
                                }
                                filtered.length <= 8 -> {
                                    val day = filtered.substring(0, 2)
                                    val month = filtered.substring(2, 4)
                                    val year = filtered.substring(4, minOf(8, filtered.length))
                                    "$day/$month/$year"
                                }
                                else -> {
                                    // Limitar a 8 dígitos (DDMMYYYY)
                                    val limited = filtered.substring(0, 8)
                                    val day = limited.substring(0, 2)
                                    val month = limited.substring(2, 4)
                                    val year = limited.substring(4, 8)
                                    "$day/$month/$year"
                                }
                            }
                        },
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
                    if (dateOfBirthError != null) {
                        Text(
                            text = dateOfBirthError,
                            fontFamily = poppinsFamily,
                            fontSize = 11.sp,
                            color = Color(0xFFEF4444),
                            modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                        )
                    }
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
                    if (passwordError != null) {
                        Text(
                            text = passwordError,
                            fontFamily = poppinsFamily,
                            fontSize = 11.sp,
                            color = Color(0xFFEF4444),
                            modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                        )
                    }
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
                    if (confirmPasswordError != null) {
                        Text(
                            text = confirmPasswordError,
                            fontFamily = poppinsFamily,
                            fontSize = 11.sp,
                            color = Color(0xFFEF4444),
                            modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                        )
                    }
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

