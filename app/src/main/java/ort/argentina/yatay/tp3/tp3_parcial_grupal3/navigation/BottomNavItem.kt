package ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
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
 * Lista de items de navegación
 */
object BottomNavItems {
    val items = listOf(
        BottomNavItem(
            title = "Home",
            icon = Icons.Default.Home,
            screen = Screen.Home
        ),
        BottomNavItem(
            title = "Profile",
            icon = Icons.Default.Person,
            screen = Screen.Profile
        ),
        BottomNavItem(
            title = "Settings",
            icon = Icons.Default.Settings,
            screen = Screen.Settings
        )
    )
}

