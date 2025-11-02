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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * Row individual de transacción
 */
@Composable
fun TransactionRow(
    title: String,
    dateTime: String,
    amount: String,
    icon: Painter,
    iconColor: Color = colorResource(R.color.light_blue),
    typeOfCharge: String
) {
    // Parse amount to determine color
    val amountValue = amount.replace(Regex("[^0-9.-]"), "").toDoubleOrNull() ?: 0.0
    val amountColor = if (amountValue >= 0)
        colorResource(R.color.fence_green)
    else
        colorResource(R.color.ocean_blue)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Surface(
            modifier = Modifier.size(40.dp),
            color = iconColor,
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            )
        }

        // Title + DateTime
        Column(
            modifier = Modifier.width(90.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.fence_green)
            )
            Text(
                text = dateTime,
                fontSize = 11.sp,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.light_blue)
            )
        }

        // Vertical Divider
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(colorResource(R.color.light_green))
        )

        // Type of Charge
        Text(
            text = typeOfCharge,
            fontSize = 12.sp,
            fontFamily = poppinsFamily,
            color = colorResource(R.color.fence_green),
            modifier = Modifier.width(60.dp)
        )

        // Vertical Divider
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(colorResource(R.color.light_green))
        )

        // Amount with dynamic color
        Text(
            text = amount,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFamily,
            color = amountColor,
            modifier = Modifier.width(80.dp),
            textAlign = TextAlign.End
        )
    }
}

