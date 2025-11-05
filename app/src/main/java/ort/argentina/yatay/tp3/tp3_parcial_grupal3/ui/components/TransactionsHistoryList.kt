package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
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

@Composable
fun TransactionsHistoryList(
    modifier: Modifier = Modifier
) {
    // NO SCROLL acá. El scroll lo maneja TransactionScreen.
    val april = listOf(
        Triple("Salary", "18:27 - April 30", "$4,000.00") to Triple(painterResource(R.drawable.cash), colorResource(R.color.light_blue), "Monthly"),
        Triple("Groceries", "17:00 - April 24", "-$100.00") to Triple(painterResource(R.drawable.food_bag), colorResource(R.color.vivid_blue), "Pantry"),
        Triple("Rent", "8:30 - April 15", "-$674.40") to Triple(painterResource(R.drawable.hand_key), colorResource(R.color.ocean_blue), "Rent"),
        Triple("Transport", "9:30 - April 08", "-$4.13") to Triple(painterResource(R.drawable.car), colorResource(R.color.sky_blue), "Fuel"),
    )
    val march = listOf(
        Triple("Food", "19:30 - March 31", "-$70.40") to Triple(painterResource(R.drawable.dish), colorResource(R.color.vivid_blue), "Dinner"),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        MonthHeader("April")
        april.forEach { (t, icon) ->
            TransactionRow(
                title = t.first,
                dateTime = t.second,
                amount = t.third,
                icon = icon.first,
                iconColor = icon.second,
                typeOfCharge = icon.third
            )
            if (t != april.last().first) {
                HorizontalDivider(
                    color = colorResource(R.color.light_green).copy(alpha = 0.3f),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        MonthHeader("March")
        march.forEach { (t, icon) ->
            TransactionRow(
                title = t.first,
                dateTime = t.second,
                amount = t.third,
                icon = icon.first,
                iconColor = icon.second,
                typeOfCharge = icon.third
            )
            if (t != march.last().first) {
                HorizontalDivider(
                    color = colorResource(R.color.light_green).copy(alpha = 0.3f),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun MonthHeader(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(top = 12.dp, bottom = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(R.color.fence_green),
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}