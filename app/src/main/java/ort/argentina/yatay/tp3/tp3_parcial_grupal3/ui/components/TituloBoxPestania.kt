package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

@Composable
fun TituloBoxPestania(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    visible: Boolean,
    onVisibilityToggle: () -> Unit,
    poppinsFontFamily: FontFamily
) {
    Column {
        // Label
        Text(
            text = label,
            fontSize = 14.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF093030),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Box personalizado que muestra solo puntos
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color(0xFFDFF7E2), RoundedCornerShape(12.dp))
        ) {
            // Contenido visible: mostrar puntos alineados a la izquierda
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 16.dp, end = 50.dp), // Padding izquierdo y espacio para el icono del ojo
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.puntos),
                    contentDescription = null,
                    modifier = Modifier
                        .width(60.dp)
                        .height(20.dp),
                    colorFilter = ColorFilter.tint(Color(0xFF000000))
                )
            }

            // Icono del ojo a la derecha
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onVisibilityToggle) {
                    EyeIcon(visible = visible)
                }
            }
        }
    }
}

@Composable
private fun EyeIcon(visible: Boolean) {
    Image(
        painter = painterResource(
            id = if (visible) R.drawable.eye_on else R.drawable.eye_off
        ),
        contentDescription = if (visible) "Hide PIN" else "Show PIN",
        modifier = Modifier.size(24.dp),
        colorFilter = ColorFilter.tint(Color(0xFF000000))
    )
}
