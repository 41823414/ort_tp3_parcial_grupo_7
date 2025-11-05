package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

@Composable
fun BurbujaTextoChat(
    texto: String,
    colorFondo: Color,
    alineacionIzquierda: Boolean = true, // true = izquierda, false = derecha
    modifier: Modifier = Modifier
) {
    // Fuente Poppins Light
    val poppinsLightFontFamily = FontFamily(
        Font(R.font.poppins_light, FontWeight.Light)
    )
    
    val textoColor = Color(0xFF093030) // Color fijo del texto
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = if (!alineacionIzquierda) 16.dp else 0.dp),
        contentAlignment = if (alineacionIzquierda) Alignment.CenterStart else Alignment.CenterEnd
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(colorFondo)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = texto,
                fontSize = 14.sp,
                fontFamily = poppinsLightFontFamily,
                fontWeight = FontWeight.Light,
                color = textoColor,
                modifier = Modifier.fillMaxWidth(0.75f)
            )
        }
    }
}
