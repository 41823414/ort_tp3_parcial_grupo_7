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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * Barra de progreso dinámico con estructura de dos capas:
 * - Barra negra (fondo) con % a izquierda, cubre todo el ancho
 * - Barra blanca desde la derecha ocupando (100% - progress)% del ancho, con monto total a la derecha
 */
@Composable
fun ProgressBarWithLabel(
    progress: Float,
    maxAmount: String,
    modifier: Modifier = Modifier,
    percentageText: String? = null,
    barBackgroundColor: Color = colorResource(R.color.void_black),
    barProgressColor: Color = colorResource(R.color.honeydew),
    cornerRadius: Int = 50,
    percentageTextColor: Color = Color.White,
    amountTextColor: Color = colorResource(R.color.void_black)
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Main progress bar container - Black background covering full width
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .background(
                    color = barBackgroundColor,
                    shape = RoundedCornerShape(cornerRadius.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            // Percentage text (left aligned inside black bar)
            Text(
                text = percentageText ?: "${(progress * 100).toInt()}%",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFamily,
                color = percentageTextColor,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp)
            )

            // White progress bar (from right side, width = 100% - progress)
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(1f - progress)
                    .align(Alignment.CenterEnd)
                    .background(
                        color = barProgressColor,
                        shape = RoundedCornerShape(cornerRadius.dp)
                    ),
                contentAlignment = Alignment.CenterEnd
            ) {
                // Amount text (right aligned inside white bar)
                Text(
                    text = maxAmount,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFamily,
                    color = amountTextColor,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(end = 12.dp)
                )
            }
        }
    }
}

