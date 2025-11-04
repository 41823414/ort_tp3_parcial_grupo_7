package ort.argentina.yatay.tp3.tp3_parcial_grupal3

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
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

    // Pantallas donde NO se muestra el menú inferior
    val screensWithoutBottomBar = listOf(
        Screen.Launch.route,
        Screen.OnBoarding.route,
        Screen.Login.route,
        Screen.SignUp.route,
        Screen.ForgotPassword.route,
        Screen.SecurityPin.route.split("?")[0], // Solo la ruta base sin parámetros
        Screen.NewPassword.route.split("?")[0], // Solo la ruta base sin parámetros
        Screen.PasswordChanged.route
    )

    // Solo mostrar bottomBar si no estamos en una pantalla de auth/launch
    val showBottomBar = currentRoute !in screensWithoutBottomBar

    Scaffold(
        topBar = { },
        bottomBar = {
            if (showBottomBar) {
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

