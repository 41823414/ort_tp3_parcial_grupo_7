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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.launch.LaunchScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.onboarding.OnBoardingScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth.LoginScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth.SignUpScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth.ForgotPasswordScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth.SecurityPinScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth.NewPasswordScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.auth.PasswordChangedScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.home.HomeScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.balance.AccountBalanceScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.transaction.TransactionScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.categories.CategoriesScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.ProfileScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.notifications.NotificationsScreen

/**
 * NAVEGACIÓN - Grafo de navegación de la app
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Transaction.route
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Launch Screen
        composable(Screen.Launch.route) {
            LaunchScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Launch.route) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route) {
                        popUpTo(Screen.Launch.route) { inclusive = true }
                    }
                },
                onNavigateToForgotPassword = {
                    navController.navigate(Screen.ForgotPassword.route) {
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
                onNextStep = { email ->
                    navController.navigate(Screen.SecurityPin.createRoute(email))
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        // Security Pin Screen
        composable(
            route = Screen.SecurityPin.route,
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            SecurityPinScreen(
                email = email,
                onAcceptPin = { pin ->
                    navController.navigate(Screen.NewPassword.createRoute(email))
                },
                onSendAgain = {
                    // TODO: Implementar lógica de reenvío
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }

        // New Password Screen
        composable(
            route = Screen.NewPassword.route,
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            NewPasswordScreen(
                email = email,
                onChangePasswordSuccess = {
                    navController.navigate(Screen.PasswordChanged.route) {
                        // Limpiar el back stack hasta Login
                        popUpTo(Screen.Login.route) { inclusive = false }
                    }
                }
            )
        }

        // Password Changed Screen
        composable(Screen.PasswordChanged.route) {
            PasswordChangedScreen(
                onGoLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Home Screen
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToBalance = { navController.navigate(Screen.AccountBalance.route) },
                onNavigateToTransactions = { navController.navigate(Screen.Transaction.route) },
                onNavigateToCategories = { navController.navigate(Screen.Categories.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) },
                onShowNotifications = { navController.navigate(Screen.Notifications.route) }
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
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Notifications Screen
        composable(Screen.Notifications.route) {
            NotificationsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

