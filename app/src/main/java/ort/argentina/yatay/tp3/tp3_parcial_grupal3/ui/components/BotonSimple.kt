package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

@Composable
fun BotonSimple(
    texto: String,
    colorFondo: Color,
    fillMaxWidth: Float,
    height: Int,
    fontSize: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val poppinsSemiBold = FontFamily(
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )
    
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(fillMaxWidth)
            .height(height.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorFondo
        ),
        shape = RoundedCornerShape(25.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = texto,
            fontSize = fontSize.sp,
            fontFamily = poppinsSemiBold,
            fontWeight = FontWeight.SemiBold,
            //color = Color(0xFF0E3E3E)
            color = Color(0xFF093030)
        )
    }
}
