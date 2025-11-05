package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

@Composable
fun LoadingSecurityScreen(
    message: String? = null,
    destinationRoute: String? = null,
    onNavigateToDestination: (String) -> Unit = {}
) {
    val backgroundColor = Color(0xFF00D09E)
    val textColor = Color(0xFFDFF7E2)

    // Fuente Poppins SemiBold
    val poppinsSemiBold = FontFamily(
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )

    // Lista de imágenes para la animación
    val animationImages = listOf(
        R.drawable.loading_animation_1,
        R.drawable.loading_animation_2,
        R.drawable.loading_animation_3,
        R.drawable.loading_animation_4
    )

    // Estado para controlar qué imagen mostrar
    var currentImageIndex by remember { mutableStateOf(0) }

    // Verificar si estamos en la última imagen
    val isLastImage = currentImageIndex == animationImages.size - 1

    // Animación que cambia las imágenes cada 500ms y se detiene en la última
    LaunchedEffect(Unit) {
        while (currentImageIndex < animationImages.size - 1) {
            delay(500) // Cambia cada 500 milisegundos
            currentImageIndex = currentImageIndex + 1
        }
        // Cuando la animación termine, esperar un poco más para mostrar el texto (si existe)
        // y luego navegar al destino si está disponible
        if (message != null) {
            delay(1500) // Esperar 1.5 segundos para mostrar el mensaje de éxito
        } else {
            delay(500) // Si no hay texto, esperar solo medio segundo
        }
        // Navegar al destino si está disponible
        if (destinationRoute != null) {
            onNavigateToDestination(destinationRoute)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = animationImages[currentImageIndex]),
                contentDescription = "Loading animation",
                modifier = Modifier.size(200.dp) // Ajusta el tamaño según necesites
            )

            // Mostrar el texto solo cuando llegue a la última imagen
            if (isLastImage && message != null) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = message,
                    fontSize = 20.sp,
                    fontFamily = poppinsSemiBold,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}