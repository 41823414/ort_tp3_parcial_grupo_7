package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * Componente para mostrar cada notificación
 */
@Composable
fun NotificationItem(
    iconResId: Int,
    iconBackgroundColor: Color = colorResource(R.color.caribbean_green),
    title: String,
    description: String,
    dateTime: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Icon Box
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = iconBackgroundColor,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(iconResId),
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        // Content Column
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Title
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.void_black)
            )

            // Description
            Text(
                text = description,
                fontSize = 12.sp,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.void_black).copy(alpha = 0.7f)
            )

            // Date/Time (Right aligned)
            Text(
                text = dateTime,
                fontSize = 11.sp,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.ocean_blue),
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

