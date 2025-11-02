package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * 1. Header - Saludo + Notificaciones
 */
@Composable
fun HomeHeader(onNotificationClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .padding(horizontal = 0.dp, vertical = 16.dp)
            .background(colorResource(R.color.caribbean_green)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hi, Welcome Back",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.void_black)
            )
            Text(
                text = "Good Morning",
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                color = colorResource(R.color.void_black).copy(alpha = 0.8f)
            )
        }

        Surface(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(50)),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            IconButton(onClick = onNotificationClick) {
                Icon(
                    painter = painterResource(R.drawable.bell),
                    contentDescription = "Notifications",
                    tint = colorResource(R.color.void_black),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

