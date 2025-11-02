package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * 3.2 Period Selector - Daily, Weekly, Monthly
 */
@Composable
fun PeriodSelector(
    selectedPeriod: String,
    onPeriodChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val periods = listOf("Daily", "Weekly", "Monthly")

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.light_green),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        periods.forEach { period ->
            Button(
                onClick = { onPeriodChange(period) },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (period == selectedPeriod)
                        colorResource(R.color.caribbean_green)
                    else
                        Color.Transparent,
                    contentColor = if (period == selectedPeriod)
                        Color.White
                    else
                        colorResource(R.color.fence_green)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = period,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFamily
                )
            }
        }
    }
}

