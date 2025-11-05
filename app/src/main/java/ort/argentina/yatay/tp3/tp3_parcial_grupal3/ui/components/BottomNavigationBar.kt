package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation.Screen
import androidx.compose.material3.MaterialTheme

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val route = navController.currentBackStackEntryAsState().value?.destination?.route
    val isDarkMode = MaterialTheme.colorScheme.surface == colorResource(R.color.dark_bg)

    val backgroundColor = if (isDarkMode) {
        colorResource(R.color.cyprus)
    } else {
        colorResource(R.color.card_mint)
    }

    Surface(
        color = backgroundColor,
        shadowElevation = 10.dp,
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .height(84.dp)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Item(
                icon = Icons.Outlined.Home,
                selected = route == Screen.Home.route,
                isDarkMode = isDarkMode
            ) { navController.navigate(Screen.Home.route) }

            Item(
                icon = Icons.Outlined.QueryStats,
                selected = route == Screen.AccountBalance.route,
                isDarkMode = isDarkMode
            ) { navController.navigate(Screen.AccountBalance.route) }

            Item(
                icon = Icons.Outlined.SwapHoriz,
                selected = route == Screen.Transaction.route,
                isDarkMode = isDarkMode
            ) { navController.navigate(Screen.Transaction.route) }

            Item(
                icon = Icons.Outlined.Layers,
                selected = route == Screen.Categories.route,
                isDarkMode = isDarkMode
            ) { navController.navigate(Screen.Categories.route) }

            Item(
                icon = Icons.Outlined.Person,
                selected = route == Screen.Profile.route,
                isDarkMode = isDarkMode
            ) { navController.navigate(Screen.Profile.route) }
        }
    }
}

@Composable
private fun Item(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    isDarkMode: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (selected) colorResource(R.color.caribbean_green)
        else Color.Transparent

    val iconColor = when {
        selected -> colorResource(R.color.void_black)
        isDarkMode -> Color.White
        else -> colorResource(R.color.void_black)
    }

    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(26.dp)
        )
    }
}