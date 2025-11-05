package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

/**
 * 3.3 Transaction List - Salary, Groceries, Rent
 */
@Composable
fun TransactionList(
    modifier: Modifier = Modifier,
    onNavigateToTransactions: () -> Unit
) {
    val transactions = listOf(
        Triple("Salary", "18:27 - April 30", "$4,000.00") to Triple(painterResource(R.drawable.cash), colorResource(R.color.light_blue), "Monthly"),
        Triple("Groceries", "17:00 - April 24", "-$100.00") to Triple(painterResource(R.drawable.food_bag), colorResource(R.color.vivid_blue), "Pantry"),
        Triple("Rent", "8:30 - April 15", "-$674.40") to Triple(painterResource(R.drawable.hand_key), colorResource(R.color.ocean_blue), "Rent")
    )

    Column(modifier = modifier.fillMaxWidth()) {
        transactions.forEach { (transactionData, iconData) ->
            TransactionRow(
                title = transactionData.first,
                dateTime = transactionData.second,
                amount = transactionData.third,
                icon = iconData.first,
                iconColor = iconData.second,
                typeOfCharge = iconData.third
            )
            if (transactionData != transactions.last().first) {
                HorizontalDivider(
                    color = colorResource(R.color.light_green).copy(alpha = 0.3f),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}