package ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.launch.LaunchScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.onboarding.OnBoardingScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth.LoginScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth.SignUpScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth.ForgotPasswordScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.home.HomeScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.balance.AccountBalanceScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.transaction.TransactionScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.categories.CategoriesScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.ProfileScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.ProfileScreenEdit
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.SecurityScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.notifications.NotificationsDialog

/**
 * NAVEGACIÓN - Grafo de navegación de la app
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Launch.route
) {
    var showNotifications by remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Launch Screen
        composable(Screen.Launch.route) {
            LaunchScreen(
                onNavigateToOnBoarding = {
                    navController.navigate(Screen.OnBoarding.route) {
                        popUpTo(Screen.Launch.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Launch.route) { inclusive = true }
                    }
                }
            )
        }

        // OnBoarding Screen
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.OnBoarding.route) { inclusive = true }
                    }
                },
                onSkip = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.OnBoarding.route) { inclusive = true }
                    }
                }
            )
        }

        // Login Screen
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToSignUp = { navController.navigate(Screen.SignUp.route) },
                onNavigateToForgotPassword = { navController.navigate(Screen.ForgotPassword.route) },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // Sign Up Screen
        composable(Screen.SignUp.route) {
            SignUpScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.SignUp.route) { inclusive = true }
                    }
                }
            )
        }

        // Forgot Password Screen
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Home Screen
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToBalance = { navController.navigate(Screen.AccountBalance.route) },
                onNavigateToTransactions = { navController.navigate(Screen.Transaction.route) },
                onNavigateToCategories = { navController.navigate(Screen.Categories.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) },
                onShowNotifications = { showNotifications = true }
            )
        }

        // Account Balance Screen
        composable(Screen.AccountBalance.route) {
            AccountBalanceScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Transaction Screen
        composable(Screen.Transaction.route) {
            TransactionScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Categories Screen
        composable(Screen.Categories.route) {
            CategoriesScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Profile Screen
        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onEditProfile = { navController.navigate(Screen.ProfileEdit.route) },
                onSecurity = { navController.navigate(Screen.Security.route) },
                onSetting = { /* TODO: Implementar navegación a Setting */ },
                onHelp = { /* TODO: Implementar navegación a Help */ },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Profile Edit Screen
        composable(Screen.ProfileEdit.route) {
            ProfileScreenEdit(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Security Screen
        composable(Screen.Security.route) {
            SecurityScreen(
                onNavigateBack = { navController.popBackStack() },
                onChangePin = { /* TODO: Implementar navegación a Change Pin */ },
                onFingerprint = { /* TODO: Implementar navegación a Fingerprint */ },
                onTermsAndConditions = { /* TODO: Implementar navegación a Terms And Conditions */ }
            )
        }
    }

    // Notifications Dialog (se muestra por encima de cualquier pantalla)
    if (showNotifications) {
        NotificationsDialog(
            onDismiss = { showNotifications = false }
        )
    }
}

