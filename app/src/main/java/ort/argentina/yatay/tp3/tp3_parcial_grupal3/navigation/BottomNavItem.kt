package ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * NAVEGACIÓN - Items del menú de navegación inferior
 */
data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

/**
 * Lista de items de navegación inferior
 * Puedes personalizar estos items según las necesidades de tu app
 */
object BottomNavItems {
    val items = listOf(
        BottomNavItem(
            title = "Home",
            icon = Icons.Default.Home,
            screen = Screen.Home
        ),
        BottomNavItem(
            title = "Balance",
            icon = Icons.Default.AccountBox,
            screen = Screen.AccountBalance
        ),
        BottomNavItem(
            title = "Transactions",
            icon = Icons.Default.Add,
            screen = Screen.Transaction
        ),
        BottomNavItem(
            title = "Categories",
            icon = Icons.Default.Menu,
            screen = Screen.Categories
        ),
        BottomNavItem(
            title = "Profile",
            icon = Icons.Default.Person,
            screen = Screen.Profile
        )
    )
}

