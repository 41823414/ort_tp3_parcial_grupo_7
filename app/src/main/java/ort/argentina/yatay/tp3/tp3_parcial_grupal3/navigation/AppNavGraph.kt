package ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.HomeScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.ProfileScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.SettingsScreen

/**
 * NAVEGACIÓN - Grafo de navegación de la app
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }

        composable(Screen.Profile.route) {
            ProfileScreen()
        }

        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}

