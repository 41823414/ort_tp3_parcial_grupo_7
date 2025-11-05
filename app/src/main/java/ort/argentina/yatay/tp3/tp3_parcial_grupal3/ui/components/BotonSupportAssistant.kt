package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

@Composable
fun BotonSupportAssistant(
    titulo: String,
    contenido: String,
    fecha: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconPainter: Painter? = null
) {
    // Fuentes Poppins
    val poppinsMediumFontFamily = FontFamily(
        Font(R.font.poppins_medium, FontWeight.Medium)
    )
    
    val poppinsLightFontFamily = FontFamily(
        Font(R.font.poppins_light, FontWeight.Light)
    )
    
    val primaryColor = Color(0xFF00C896) // Verde principal para el icono
    
    // Box grande con fondo #DFF7E2
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .background(Color(0xFFDFF7E2))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono a la izquierda con fondo verde
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(primaryColor),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = iconPainter ?: painterResource(id = R.drawable.soporte_asistente),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Textos en el centro
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 100.dp), // Espacio para el box de fecha
                verticalArrangement = Arrangement.Center
            ) {
                // Título
                Text(
                    text = titulo,
                    fontSize = 15.sp,
                    fontFamily = poppinsMediumFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF093030)
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // Contenido
                Text(
                    text = contenido,
                    fontSize = 13.sp,
                    fontFamily = poppinsLightFontFamily,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF666666)
                )
            }
        }
        
        // Box pequeño con fondo #F1FFF3 en la esquina inferior derecha
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .height(19.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF1FFF3))
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = fecha,
                fontSize = 12.sp,
                fontFamily = poppinsLightFontFamily,
                fontWeight = FontWeight.Light,
                color = Color(0xFF666666)
            )
        }
    }
}
