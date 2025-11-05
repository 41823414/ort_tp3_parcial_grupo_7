package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TituloBox(
    title: String,
    value: String,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Título
        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0E3E3E),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        // Box con el valor
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .height(40.dp)
                .background(Color(0xFFDFF7E2), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = value,
                fontSize = 14.sp,
                fontFamily = fontFamily,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
