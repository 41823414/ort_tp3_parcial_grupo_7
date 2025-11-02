package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * Barra de progreso dinámico con etiqueta de porcentaje y monto máximo
 * Muestra una barra de fondo oscuro con una barra de progreso blanca en el frente
 */
@Composable
fun ProgressBarWithLabel(
    progress: Float,
    maxAmount: String,
    modifier: Modifier = Modifier,
    percentageText: String? = null,
    barBackgroundColor: Color = Color.Black.copy(alpha = 0.7f),
    barProgressColor: Color = Color.White,
    cornerRadius: Int = 12
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Labels row (Percentage and Max Amount)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = percentageText ?: "${(progress * 100).toInt()}%",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFamily,
                color = Color.White
            )
            Text(
                text = maxAmount,
                fontSize = 12.sp,
                fontFamily = poppinsFamily,
                color = Color.White.copy(alpha = 0.8f)
            )
        }

        // Progress bar container (background + progress indicator)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = barProgressColor,
                    shape = RoundedCornerShape(cornerRadius.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            // Background (dark bar) on top
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
                    .background(
                        color = barBackgroundColor,
                        shape = RoundedCornerShape(cornerRadius.dp)
                    )
            )

            // Centered text with max amount (on top of both bars)
            Text(
                text = maxAmount,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.void_black),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

