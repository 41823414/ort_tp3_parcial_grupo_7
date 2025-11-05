package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

@Composable
fun TransactionRow(
    title: String,
    dateTime: String,
    amount: String,
    icon: Painter,
    iconColor: Color = colorResource(R.color.light_blue),
    typeOfCharge: String
) {
    val amountValue = amount.replace(Regex("[^0-9.-]"), "")
        .toDoubleOrNull() ?: 0.0

    val amountColor = if (amountValue >= 0)
        colorResource(R.color.fence_green)
    else
        colorResource(R.color.vivid_blue)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícono
        Surface(
            modifier = Modifier.size(48.dp),
            color = iconColor,
            shape = RoundedCornerShape(14.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier
                    .padding(10.dp)
                    .size(24.dp)
            )
        }

        // Título + fecha
        Column(
            modifier = Modifier.weight(1.2f)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.fence_green),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = dateTime,
                fontSize = 12.sp,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.light_blue),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Separador
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(colorResource(R.color.light_green))
        )

        // Tipo
        Text(
            text = typeOfCharge,
            fontSize = 14.sp,
            fontFamily = poppinsFamily,
            color = colorResource(R.color.fence_green),
            modifier = Modifier.weight(0.8f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Separador
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(colorResource(R.color.light_green))
        )

        // Monto
        Text(
            text = amount,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFamily,
            color = amountColor,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            modifier = Modifier
                .widthIn(min = 112.dp)           // evita quebrar/“agregar 0”
                .wrapContentWidth(Alignment.End),
            textAlign = TextAlign.End,
            softWrap = false
        )
    }
}