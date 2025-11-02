package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
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
 * Progress Bar con etiqueta
 */
@Composable
fun ProgressBarWithLabel(
    progress: Float,
    maxAmount: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${(progress * 100).toInt()}%",
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
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color.White,
            trackColor = Color.White.copy(alpha = 0.2f)
        )
    }
}

