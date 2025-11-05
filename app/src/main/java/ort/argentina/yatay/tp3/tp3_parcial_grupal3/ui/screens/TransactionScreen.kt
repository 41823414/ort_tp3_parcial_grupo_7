package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.ProgressBarWithLabel
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.TransactionsHistoryList
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

@Composable
fun TransactionScreen(
    onNavigateBack: () -> Unit = {}
) {
    val topColor   = Color(0xFF00B686)
    val panelColor = colorResource(R.color.panel_soft)
    val baseBackgroundColor = colorResource(R.color.caribbean_green)

    val scroll = rememberScrollState()

    // Fondo base de toda la pantalla = caribbean_green
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(baseBackgroundColor)
    ) {
        // Lona verde superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(topColor)
        )

        // Contenido scrolleable (UN solo scroll)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(scroll)
        ) {
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Transaction",
                    color = colorResource(R.color.title_on_top),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFamily,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Icon(
                    painter = painterResource(R.drawable.bell),
                    contentDescription = "Notifications",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(Modifier.height(12.dp))

            // Contenedor al 90%
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Card Total Balance
                    Surface(
                        color = colorResource(R.color.card_mint),
                        shape = RoundedCornerShape(16.dp),
                        shadowElevation = 0.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 76.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Total Balance",
                                color = colorResource(R.color.title_muted),
                                fontSize = 15.sp,
                                fontFamily = poppinsFamily
                            )
                            Text(
                                text = "$7,783.00",
                                color = colorResource(R.color.amount_dark),
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = poppinsFamily
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Totales
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(R.drawable.cash),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = "Total Balance",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontFamily = poppinsFamily
                                )
                            }
                            Text(
                                text = "$7,783.00",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = poppinsFamily
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.End
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(R.drawable.bag),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = "Total Expense",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontFamily = poppinsFamily
                                )
                            }
                            Text(
                                text = "-$1,187.40",
                                color = Color(0xFF9EE3FF),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = poppinsFamily
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))

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

                    Spacer(Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.tick),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "30% Of Your Expenses, Looks Good.",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = poppinsFamily
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // Panel redondeado + lista (sobre fondo panel_soft)
            Surface(
                color = panelColor,
                shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Contenido al 90% de ancho dentro de la caja blanca
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(0.9f)
                        ) {
                            TransactionsHistoryList(
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(colorResource(R.color.honeydew))
            )
        }
    }
}