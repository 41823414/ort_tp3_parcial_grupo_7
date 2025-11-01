package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

/**
 * Home Screen - Pantalla principal de la aplicación
 */
@Composable
fun HomeScreen(
    onNavigateToBalance: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategories: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onShowNotifications: () -> Unit = {}
) {
    var selectedPeriod by remember { mutableStateOf("Monthly") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.caribbean_green))
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            // 1. Header
            HomeHeader(onNotificationClick = onShowNotifications)

            // 2. Balance Section
            BalanceSection()

            // 3. Main Content Card
            MainContentCard(
                selectedPeriod = selectedPeriod,
                onPeriodChange = { selectedPeriod = it },
                onNavigateToTransactions = onNavigateToTransactions
            )
        }
    }
}

/**
 * 1. Header - Saludo + Notificaciones
 */
@Composable
private fun HomeHeader(onNotificationClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.caribbean_green))
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hi, Welcome Back",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.void_black)
            )
            Text(
                text = "Good Morning",
                fontSize = 14.sp,
                color = colorResource(R.color.void_black).copy(alpha = 0.8f)
            )
        }

        Surface(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(50)),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            IconButton(onClick = onNotificationClick) {
                Icon(
                    painter = painterResource(R.drawable.bell),
                    contentDescription = "Notifications",
                    tint = colorResource(R.color.caribbean_green),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

/**
 * 2. Balance Section - Saldo total, gastos, y progress bar
 */
@Composable
private fun BalanceSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.caribbean_green))
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        // 2.1 Total Balance / Total Expense Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BalanceCard(
                title = "Total Balance",
                amount = "$7,783.00",
                modifier = Modifier.weight(1f)
            )
            BalanceCard(
                title = "Total Expense",
                amount = "-$1,187.40",
                amountColor = colorResource(R.color.vivid_blue),
                modifier = Modifier.weight(1f)
            )
        }

        // 2.2 Progress Bar
        ProgressBarWithLabel(
            progress = 0.3f,
            maxAmount = "$20,000.00"
        )

        // 2.3 Status Text
        Text(
            text = "30% Of Your Expenses, Looks Good.",
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

/**
 * Card para mostrar Balance o Expense
 */
@Composable
private fun BalanceCard(
    title: String,
    amount: String,
    amountColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.8f)
        )
        Text(
            text = amount,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = amountColor,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

/**
 * Progress Bar con etiqueta
 */
@Composable
private fun ProgressBarWithLabel(
    progress: Float,
    maxAmount: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${(progress * 100).toInt()}%",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = maxAmount,
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color.White,
            trackColor = Color.White.copy(alpha = 0.2f)
        )
    }
}

/**
 * 3. Main Content Card - Ingresos/Gastos + Botones + Transacciones
 */
@Composable
private fun MainContentCard(
    selectedPeriod: String,
    onPeriodChange: (String) -> Unit,
    onNavigateToTransactions: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            color = colorResource(R.color.honeydew),
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 3.1 Income/Expense Box
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.caribbean_green),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Car Icon Box
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                color = colorResource(R.color.caribbean_green),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .border(2.dp, Color.White, RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.car),
                            contentDescription = "Savings",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Savings\nOn Goals",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            TransactionItem(
                                label = "Revenue Last Week",
                                amount = "$4,000.00",
                                amountColor = Color.White
                            )
                            TransactionItem(
                                label = "Food Last Week",
                                amount = "-$100.00",
                                amountColor = colorResource(R.color.vivid_blue)
                            )
                        }
                    }
                }

                // 3.2 Period Selector Buttons
                PeriodSelector(
                    selectedPeriod = selectedPeriod,
                    onPeriodChange = onPeriodChange,
                    modifier = Modifier.padding(top = 16.dp)
                )

                // 3.3 Transaction List
                TransactionList(
                    modifier = Modifier.padding(top = 16.dp),
                    onNavigateToTransactions = onNavigateToTransactions
                )
            }
        }
    }
}

/**
 * Item de transacción (Revenue/Food)
 */
@Composable
private fun TransactionItem(
    label: String,
    amount: String,
    amountColor: Color
) {
    Column {
        Text(
            text = label,
            fontSize = 10.sp,
            color = Color.White.copy(alpha = 0.8f)
        )
        Text(
            text = amount,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = amountColor
        )
    }
}

/**
 * 3.2 Period Selector - Daily, Weekly, Monthly
 */
@Composable
private fun PeriodSelector(
    selectedPeriod: String,
    onPeriodChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val periods = listOf("Daily", "Weekly", "Monthly")

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.honeydew),
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
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = period,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

/**
 * 3.3 Transaction List - Salary, Groceries, Rent
 */
@Composable
private fun TransactionList(
    modifier: Modifier = Modifier,
    onNavigateToTransactions: () -> Unit
) {
    val transactions = listOf(
        Triple("Salary", "18:27 - April 30", "$4,000,00") to painterResource(R.drawable.enter),
        Triple("Groceries", "17:00 - April 24", "-$100,00") to painterResource(R.drawable.bag),
        Triple("Rent", "8:30 - April 15", "-$674,40") to painterResource(R.drawable.house_keys)
    )

    Column(modifier = modifier.fillMaxWidth()) {
        transactions.forEach { (transactionData, icon) ->
            TransactionRow(
                title = transactionData.first,
                dateTime = transactionData.second,
                amount = transactionData.third,
                icon = icon,
                period = "Monthly"
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

/**
 * Row individual de transacción
 */
@Composable
private fun TransactionRow(
    title: String,
    dateTime: String,
    amount: String,
    icon: androidx.compose.ui.graphics.painter.Painter,
    period: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                color = colorResource(R.color.light_blue),
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

            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.fence_green)
                )
                Text(
                    text = dateTime,
                    fontSize = 12.sp,
                    color = colorResource(R.color.cyprus).copy(alpha = 0.7f)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = period,
                fontSize = 12.sp,
                color = colorResource(R.color.cyprus).copy(alpha = 0.7f)
            )
            Text(
                text = amount,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.cyprus)
            )
        }
    }
}

