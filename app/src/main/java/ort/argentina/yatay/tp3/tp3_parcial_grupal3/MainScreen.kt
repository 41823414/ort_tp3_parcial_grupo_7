package ort.argentina.yatay.tp3.tp3_parcial_grupal3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation.AppNavGraph
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation.Screen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BottomNavigationBar
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

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

    //showBottomBar Agus
    // Solo mostrar bottomBar si no estamos en una pantalla de auth/launch
    val showBottomBar = currentRoute !in screensWithoutBottomBar

    // ShowBottomBar original*/
    /*
    val showBottomBar = currentRoute !in listOf(
        Screen.Launch.route,
        Screen.OnBoarding.route
    )
    */

    Box(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.panel_soft))
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            // Usa la firma segura; algunas versiones no aceptan WindowInsets(0)
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                    if (showBottomBar) {
                        BottomNavigationBar(navController = navController)
                    }
                }
        ) { innerPadding ->
            AppNavGraph(
                navController = navController,
                modifier = Modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
            )
        }
    }
}