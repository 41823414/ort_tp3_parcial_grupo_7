package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlechaTituloPrincipalCampana(
    titulo: String,
    onBack: () -> Unit = {},
    onNotifications: () -> Unit = {},
    primaryColor: Color = Color(0xFF00C896),
    modifier: Modifier = Modifier
) {
    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )
    
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = titulo,
                    color = Color(0xFF093030),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Image(
                    painter = painterResource(id = R.drawable.flecha_atras),
                    contentDescription = "Back",
                    modifier = Modifier
                        .width(19.dp)
                        .height(16.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        },
        actions = {
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            
            IconButton(
                onClick = onNotifications,
                interactionSource = interactionSource
            ) {
                // Icono de notificaciones - cambia según el estado de presión
                Image(
                    painter = painterResource(
                        id = if (isPressed) R.drawable.campana_onpress else R.drawable.campana_offpress
                    ),
                    contentDescription = "Notifications",
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = primaryColor
        )
    )
}
