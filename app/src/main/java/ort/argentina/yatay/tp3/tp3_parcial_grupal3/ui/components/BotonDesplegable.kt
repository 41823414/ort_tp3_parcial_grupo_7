package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

@Composable
fun BotonDesplegable(
    titulo: String,
    contenido: String,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onExpandedChange: (Boolean) -> Unit = {}
) {
    // Fuente League Spartan para el título
    val leagueSpartanFontFamily = FontFamily(
        Font(R.font.leaguespartan_regular, FontWeight.Normal)
    )
    
    // Fuente Poppins Light para el contenido
    val poppinsLightFontFamily = FontFamily(
        Font(R.font.poppins_light, FontWeight.Light)
    )
    
    // Estado local para controlar si está expandido
    var isExpanded by remember { mutableStateOf(expanded) }
    
    Column(modifier = modifier.fillMaxWidth()) {
        // Fila con título e icono (siempre visible)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded
                    onExpandedChange(isExpanded)
                }
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Título
            Text(
                text = titulo,
                fontSize = 16.sp,
                fontFamily = leagueSpartanFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF2D5C5C),
                modifier = Modifier.weight(1f)
            )
            
            // Icono según el estado (abierto o cerrado)
            Image(
                painter = painterResource(
                    id = if (isExpanded) 
                        R.drawable.desplegable_abierto 
                    else 
                        R.drawable.desplegable_cerrado
                ),
                contentDescription = if (isExpanded) "Cerrar" else "Abrir",
                modifier = Modifier.size(24.dp)
            )
        }
        
        // Contenido (solo visible si está expandido)
        if (isExpanded) {
            Spacer(modifier = Modifier.height(8.dp))
            
            // Box con fondo claro para el contenido
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE0F2E0))
                    .padding(16.dp)
            ) {
                Text(
                    text = contenido,
                    fontSize = 13.sp,
                    fontFamily = poppinsLightFontFamily,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF2D5C5C)
                )
            }
        }
    }
}
