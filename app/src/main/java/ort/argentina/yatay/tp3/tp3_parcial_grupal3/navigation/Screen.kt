package ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation

/**
 * NAVEGACIÓN - Sealed class para definir las pantallas de la app
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Settings : Screen("settings")

    companion object {
        fun fromRoute(route: String?): Screen = when (route) {
            Home.route -> Home
            Profile.route -> Profile
            Settings.route -> Settings
            else -> Home
        }
    }
}

