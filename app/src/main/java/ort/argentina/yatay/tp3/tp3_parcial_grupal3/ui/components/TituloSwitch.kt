package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

@Composable
fun TituloSwitch(
    title: String,
    enabled: Boolean,
    onToggle: (Boolean) -> Unit,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    bottomPadding: Int = 20
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = bottomPadding.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0E3E3E)
        )
        Image(
            painter = painterResource(
                id = if (enabled)
                    R.drawable.switch_on
                else
                    R.drawable.switch_off
            ),
            contentDescription = title,
            modifier = Modifier
                .size(48.dp, 28.dp)
                .clickable { onToggle(!enabled) }
        )
    }
}
