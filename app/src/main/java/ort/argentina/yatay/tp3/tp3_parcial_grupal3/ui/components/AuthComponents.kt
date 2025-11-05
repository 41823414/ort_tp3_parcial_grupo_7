package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * COMPONENTES DE AUTENTICACIÓN - Componentes reutilizables para pantallas de auth
 */

/**
 * Header de pantalla de autenticación con fondo verde
 */
@Composable
fun AuthHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f) // 35% de la altura
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.green_emerald_start),
                        colorResource(R.color.green_emerald_end)
                    )
                ),
                shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp)
            )
            .clip(RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = colorResource(R.color.void_black)
        )
    }
}

/**
 * Campo de texto personalizado con estilo verde claro
 */
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { 
            Text(
                text = label,
                fontFamily = poppinsFamily,
                fontSize = 14.sp,
                color = Color.Black
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = poppinsFamily,
                fontSize = 14.sp,
                color = Color.Gray
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = enabled,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF5F5F5), // Gris muy claro
            unfocusedContainerColor = Color(0xFFF5F5F5), // Gris muy claro
            focusedBorderColor = colorResource(R.color.caribbean_green),
            unfocusedBorderColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            focusedLabelColor = Color.Black
        ),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontFamily = poppinsFamily,
            fontSize = 14.sp
        )
    )
}

/**
 * Campo de contraseña con toggle de visibilidad
 */
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "********",
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { 
            Text(
                text = label,
                fontFamily = poppinsFamily,
                fontSize = 14.sp,
                color = Color.Black
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = poppinsFamily,
                fontSize = 14.sp,
                color = Color.Gray
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = enabled,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = painterResource(
                        if (passwordVisible) R.drawable.eye_open else R.drawable.eye_closed
                    ),
                    contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                    tint = colorResource(R.color.dark_green),
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF5F5F5), // Gris muy claro
            unfocusedContainerColor = Color(0xFFF5F5F5), // Gris muy claro
            focusedBorderColor = colorResource(R.color.caribbean_green),
            unfocusedBorderColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            focusedLabelColor = Color.Black
        ),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontFamily = poppinsFamily,
            fontSize = 14.sp
        )
    )
}

/**
 * Botón primario verde
 */
@Composable
fun AuthPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = enabled,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.green_emerald_start),
            disabledContainerColor = Color.Gray,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

/**
 * Botón secundario con borde verde
 */
@Composable
fun AuthSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = enabled,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = colorResource(R.color.dark_green),
            containerColor = Color(0xFFF5F5F5) // Gris muy claro
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = Color.Transparent
        )
    ) {
        Text(
            text = text,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = colorResource(R.color.dark_green)
        )
    }
}

/**
 * Link de texto verde
 */
@Composable
fun AuthTextLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Bold
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = text,
            fontFamily = poppinsFamily,
            fontSize = 14.sp,
            color = colorResource(R.color.dark_green),
            fontWeight = fontWeight
        )
    }
}

/**
 * Botones de login social (Google y Facebook)
 */
@Composable
fun SocialLoginButtons(
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón Google
        IconButton(
            onClick = onGoogleClick,
            modifier = Modifier
                .size(33.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                painter = painterResource(R.drawable.google),
                contentDescription = "Login con Google",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )
        }
        
        Spacer(modifier = Modifier.width(20.dp))
        
        // Botón Facebook
        IconButton(
            onClick = onFacebookClick,
            modifier = Modifier
                .size(33.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                painter = painterResource(R.drawable.facebook),
                contentDescription = "Login con Facebook",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )
        }
    }
}

/**
 * Footer con link de registro/login
 */
@Composable
fun AuthFooter(
    text: String,
    linkText: String,
    onLinkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = buildAnnotatedString {
                append("$text ")
                withStyle(style = SpanStyle(color = colorResource(R.color.vivid_blue_accent))) {
                    append(linkText)
                }
            },
            style = androidx.compose.ui.text.TextStyle(
                fontFamily = poppinsFamily,
                fontSize = 14.sp,
                color = Color.Gray
            ),
            modifier = Modifier.clickable(onClick = onLinkClick)
        )
    }
}

/**
 * Input de PIN con 6 dígitos
 */
@Composable
fun PinInputField(
    pin: String,
    onPinChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(6) { index ->
            val digit = if (index < pin.length) pin[index].toString() else ""
            OutlinedTextField(
                value = digit,
                onValueChange = { newValue ->
                    if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                        val newPin = StringBuilder(pin.padEnd(6, ' '))
                        if (index < newPin.length) {
                            newPin[index] = newValue.firstOrNull() ?: ' '
                        }
                        onPinChange(newPin.toString().trim())
                    }
                },
                modifier = Modifier
                    .width(48.dp)
                    .height(56.dp),
                enabled = enabled,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = CircleShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = colorResource(R.color.caribbean_green),
                    unfocusedBorderColor = Color.Gray
                ),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontFamily = poppinsFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

