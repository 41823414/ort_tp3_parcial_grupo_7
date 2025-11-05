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
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon - 10%
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.10f / 0.9f)
                .aspectRatio(1f),
            color = iconColor,
            shape = RoundedCornerShape(16.dp)
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

        // Title + DateTime - 30%
        Column(
            modifier = Modifier.fillMaxWidth(0.333f)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.fence_green),
                maxLines = 1
            )
            Text(
                text = dateTime,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.ocean_blue),
                maxLines = 1
            )
        }

        // Vertical Divider
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(32.dp)
                .background(colorResource(R.color.caribbean_green))
        )

        // Type of Charge - 25%
        Text(
            text = typeOfCharge,
            fontSize = 12.sp,
            fontFamily = poppinsFamily,
            color = colorResource(R.color.fence_green),
            modifier = Modifier
                .fillMaxWidth(0.357f)
                .padding(horizontal = 8.dp),
            maxLines = 1
        )

        // Vertical Divider
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(40.dp)
                .background(colorResource(R.color.caribbean_green))
        )

        // Amount with dynamic color - 35%
        Text(
            text = amount,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFamily,
            color = amountColor,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

