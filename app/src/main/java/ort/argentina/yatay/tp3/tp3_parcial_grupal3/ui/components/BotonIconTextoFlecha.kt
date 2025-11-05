package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

@Composable
fun BotonIconTextoFlecha(
    texto: String,
    iconPainter: Painter,
    onClick: () -> Unit,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    bottomPadding: Int = 0,
    iconWidth: androidx.compose.ui.unit.Dp = 24.dp,
    iconHeight: androidx.compose.ui.unit.Dp = 24.dp,
    iconBoxWidth: androidx.compose.ui.unit.Dp? = null,
    iconBoxHeight: androidx.compose.ui.unit.Dp? = null,
    iconBoxColor: Color? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp)
            .padding(bottom = bottomPadding.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Ícono con o sin box según los parámetros
            if (iconBoxWidth != null && iconBoxHeight != null && iconBoxColor != null) {
                // Ícono con box circular
                Box(
                    modifier = Modifier
                        .width(iconBoxWidth)
                        .height(iconBoxHeight)
                        .clip(CircleShape)
                        .background(iconBoxColor),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = iconPainter,
                        contentDescription = null,
                        modifier = Modifier
                            .width(iconWidth)
                            .height(iconHeight),
                        contentScale = ContentScale.Fit
                    )
                }
            } else {
                // Ícono sin fondo
                Image(
                    painter = iconPainter,
                    contentDescription = null,
                    modifier = Modifier
                        .width(iconWidth)
                        .height(iconHeight),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = texto,
                fontSize = 16.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF0E3E3E)
            )
        }

        // Flecha derecha
        Image(
            painter = painterResource(id = R.drawable.flecha_derecha),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}
