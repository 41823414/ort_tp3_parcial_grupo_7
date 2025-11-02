package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * 2. Balance Section - Saldo total, gastos, y progress bar
 */
@Composable
fun BalanceSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ) {
            // 2.1 Total Balance / Total Expense Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Balance Column
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .border(1.dp, colorResource(R.color.void_black), RoundedCornerShape(6.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.arrow_up_left),
                                contentDescription = "Balance Up",
                                tint = colorResource(R.color.void_black),
                                modifier = Modifier
                                    .size(16.dp)
                                    .rotate(90f)
                            )
                        }
                        Text(
                            text = "Total Balance",
                            fontSize = 12.sp,
                            fontFamily = poppinsFamily,
                            color = colorResource(R.color.void_black)
                        )
                    }
                    Text(
                        text = "$7,783.00",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFamily,
                        color = colorResource(R.color.honeydew)
                    )
                }

                // Vertical Divider Line
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(60.dp)
                        .background(colorResource(R.color.light_green))
                )

                // Expense Column
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .border(1.dp, colorResource(R.color.void_black), RoundedCornerShape(6.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.arrow_down_right),
                                contentDescription = "Expense Down",
                                tint = colorResource(R.color.void_black),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Text(
                            text = "Total Expense",
                            fontSize = 12.sp,
                            fontFamily = poppinsFamily,
                            color = colorResource(R.color.void_black)
                        )
                    }
                    Text(
                        text = "-$1,187.40",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFamily,
                        color = colorResource(R.color.ocean_blue)
                    )
                }
            }

            // 2.2 Progress Bar
            ProgressBarWithLabel(
                progress = 0.3f,
                maxAmount = "$20,000.00",
                percentageText = "30%",
                barBackgroundColor = colorResource(R.color.void_black),
                barProgressColor = colorResource(R.color.honeydew),
                cornerRadius = 50,
                percentageTextColor = Color.White,
                amountTextColor = colorResource(R.color.void_black)
            )

            Row(
                modifier = Modifier.padding(top = 8.dp),) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .border(1.dp, colorResource(R.color.void_black), RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.tick),
                        contentDescription = "Expense Down",
                        tint = colorResource(R.color.void_black),
                        modifier = Modifier.size(15.dp)
                    )
                }
                // 2.3 Status Text
                Text(
                    modifier = Modifier.padding(start = 4.dp, bottom = 1.dp),
                    text = "30% Of Your Expenses, Looks Good.",
                    fontSize = 12.sp,
                    fontFamily = poppinsFamily,
                    color = colorResource(R.color.void_black)
                )
            }
        }
    }
}

