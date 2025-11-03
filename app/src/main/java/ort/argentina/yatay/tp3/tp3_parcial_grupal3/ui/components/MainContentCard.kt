package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * 3. Main Content Card - Ingresos/Gastos + Botones + Transacciones
 */
@Composable
fun MainContentCard(
    selectedPeriod: String,
    onPeriodChange: (String) -> Unit,
    onNavigateToTransactions: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)),
            color = colorResource(R.color.honeydew),
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, bottom = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
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
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        // Left Column: Car Icon with white border circle + Title
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.3f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .border(2.dp, Color.White, shape = RoundedCornerShape(50)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.car),
                                    contentDescription = "Car",
                                    tint = colorResource(R.color.void_black),
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                            Text(
                                text = "Savings\nOn Goals",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = poppinsFamily,
                                color = colorResource(R.color.void_black),
                                textAlign = TextAlign.Center,
                            )
                        }

                        // Vertical divider
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(100.dp)
                                .background(colorResource(R.color.light_green))
                        )

                        // Column with two rows (Revenue and Food)
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(0.7f),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            // Revenue Row
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.cash),
                                    contentDescription = "Revenue",
                                    tint = colorResource(R.color.void_black),
                                    modifier = Modifier
                                        .size(42.dp)
                                )
                                Column(
                                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                                ) {
                                    Text(
                                        text = "Revenue Last Week",
                                        fontSize = 11.sp,
                                        fontFamily = poppinsFamily,
                                        color = colorResource(R.color.void_black)
                                    )
                                    Text(
                                        text = "$4,000.00",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = poppinsFamily,
                                        color = colorResource(R.color.void_black)
                                    )
                                }
                            }

                            // Horizontal divider
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .height(1.dp)
                                    .background(colorResource(R.color.light_green))
                            )

                            // Food Row
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.dish),
                                    contentDescription = "Food",
                                    tint = colorResource(R.color.void_black),
                                    modifier = Modifier.size(42.dp)
                                )
                                Column(
                                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                                ) {
                                    Text(
                                        text = "Food Last Week",
                                        fontSize = 11.sp,
                                        fontFamily = poppinsFamily,
                                        color = colorResource(R.color.void_black)
                                    )
                                    Text(
                                        text = "-$100.00",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = poppinsFamily,
                                        color = colorResource(R.color.vivid_blue)
                                    )
                                }
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
}

