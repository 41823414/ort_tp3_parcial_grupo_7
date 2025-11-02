package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.NotificationItem
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.poppinsFamily

/**
 * Data class para las notificaciones
 */
data class Notification(
    val id: Int,
    val iconResId: Int,
    val iconBackgroundColor: Color,
    val title: String,
    val description: String,
    val dateTime: String,
    val category: String
)

/**
 * Notifications Screen - Pantalla de notificaciones
 */
@Composable
fun NotificationsScreen(
    onNavigateBack: () -> Unit = {}
) {
    // Sample notifications data
    val notifications = listOf(
        Notification(
            id = 1,
            iconResId = R.drawable.bell,
            iconBackgroundColor = colorResource(R.color.caribbean_green),
            title = "New Deposit!",
            description = "Set up your automatic savings to meet your savings goal...",
            dateTime = "17:00 - April 24",
            category = "Today"
        ),
        Notification(
            id = 2,
            iconResId = R.drawable.bell,
            iconBackgroundColor = colorResource(R.color.caribbean_green),
            title = "New Update",
            description = "Set up your automatic savings to meet your savings goal...",
            dateTime = "17:00 - April 24",
            category = "Today"
        ),
        Notification(
            id = 3,
            iconResId = R.drawable.bell,
            iconBackgroundColor = colorResource(R.color.ocean_blue),
            title = "Transactions",
            description = "A new transaction has been registered\nGroceries | Pantry | -\$100.00",
            dateTime = "17:00 - April 24",
            category = "Yesterday"
        ),
        Notification(
            id = 4,
            iconResId = R.drawable.bell,
            iconBackgroundColor = colorResource(R.color.caribbean_green),
            title = "Reminder!",
            description = "Set up your automatic savings to meet your savings goal...",
            dateTime = "17:00 - April 24",
            category = "Yesterday"
        ),
        Notification(
            id = 5,
            iconResId = R.drawable.bell,
            iconBackgroundColor = colorResource(R.color.caribbean_green),
            title = "Expense Record",
            description = "We recommend that you be more attentive to your Finances...",
            dateTime = "17:00 - April 24",
            category = "This Weekend"
        ),
        Notification(
            id = 6,
            iconResId = R.drawable.bell,
            iconBackgroundColor = colorResource(R.color.ocean_blue),
            title = "Transactions",
            description = "A new transaction has been registered\nFood | Dinner | -\$70.40",
            dateTime = "17:00 - April 24",
            category = "This Weekend"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.honeydew))
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.caribbean_green))
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = stringResource(R.string.notifications_title),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFamily,
                color = Color.White
            )

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.bell),
                    contentDescription = "Notifications",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Notifications List
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                val groupedNotifications = notifications.groupBy { it.category }
                groupedNotifications.forEach { (category, notificationList) ->
                    // Category Header
                    item {
                        Text(
                            text = category,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = poppinsFamily,
                            color = colorResource(R.color.void_black),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                    }

                    // Notifications in this category
                    items(notificationList) { notification ->
                        NotificationItem(
                            iconResId = notification.iconResId,
                            iconBackgroundColor = notification.iconBackgroundColor,
                            title = notification.title,
                            description = notification.description,
                            dateTime = notification.dateTime
                        )

                        // Divider
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(colorResource(R.color.light_green).copy(alpha = 0.3f))
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

