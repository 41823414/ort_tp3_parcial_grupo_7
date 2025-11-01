package ort.argentina.yatay.tp3.tp3_parcial_grupal3

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation.AppNavGraph
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation.Screen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BottomNavigationBar

/**
 * MAIN SCREEN - Pantalla principal que integra navegación y barra inferior
 */
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute !in listOf(
        Screen.Launch.route,
        Screen.OnBoarding.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                // tu bottom bar actual
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

