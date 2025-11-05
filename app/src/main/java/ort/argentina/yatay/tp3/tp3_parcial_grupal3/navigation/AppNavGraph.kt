package ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
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
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.SecurityScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.FingerprintScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.FingerprintViewScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.FingerprintAddScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.ChangePinScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.TermsAndConditionsScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.LoadingSecurityScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.ProfileSettingsScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.ProfileNotificationSettingsScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.ProfilePasswordSettingsScreen
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile.DeleteAccountScreen
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
                onSetting = { navController.navigate(Screen.ProfileSettings.route) },
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
                onChangePin = { navController.navigate(Screen.ChangePin.route) },
                onFingerprint = { navController.navigate(Screen.Fingerprint.route) },
                onTermsAndConditions = { navController.navigate(Screen.TermsAndConditions.route) }
            )
        }

        // Fingerprint Screen
        composable(Screen.Fingerprint.route) {
            FingerprintScreen(
                onNavigateBack = { navController.popBackStack() },
                onJohnFingerprint = { navController.navigate(Screen.FingerprintView.route) },
                onAddFingerprint = { navController.navigate(Screen.FingerprintAdd.route) }
            )
        }

        // Fingerprint Add Screen
        composable(Screen.FingerprintAdd.route) {
            FingerprintAddScreen(
                onBack = { navController.popBackStack() },
                onUseTouchId = {
                    val encodedMessage = URLEncoder.encode("Fingerprint Has been Changed successfully", "UTF-8")
                    val encodedDestination = URLEncoder.encode(Screen.Fingerprint.route, "UTF-8")
                    navController.navigate("${Screen.LoadingSecurity.route}/$encodedMessage/$encodedDestination")
                }
            )
        }

        // Fingerprint View Screen
        composable(Screen.FingerprintView.route) {
            FingerprintViewScreen(
                onBack = { navController.popBackStack() },
                onDelete = {
                    val encodedMessage = URLEncoder.encode("The fingerprint has been successfully deleted.", "UTF-8")
                    val encodedDestination = URLEncoder.encode(Screen.Security.route, "UTF-8")
                    navController.navigate("${Screen.LoadingSecurity.route}/$encodedMessage/$encodedDestination")
                }
            )
        }

        // Change Pin Screen
        composable(Screen.ChangePin.route) {
            ChangePinScreen(
                onBack = { navController.popBackStack() },
                onChangePinSuccess = { message ->
                    val encodedMessage = URLEncoder.encode(message, "UTF-8")
                    val encodedDestination = URLEncoder.encode(Screen.Security.route, "UTF-8")
                    navController.navigate("${Screen.LoadingSecurity.route}/$encodedMessage/$encodedDestination")
                }
            )
        }

        // Loading Security Screen (con argumentos de texto, destino y opcionalmente popUpTo)
        composable(
            route = "${Screen.LoadingSecurity.route}/{message}/{destination}",
            arguments = listOf(
                navArgument("message") { type = NavType.StringType },
                navArgument("destination") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val encodedMessage = backStackEntry.arguments?.getString("message") ?: ""
            val encodedDestination = backStackEntry.arguments?.getString("destination") ?: ""
            val message = URLDecoder.decode(encodedMessage, StandardCharsets.UTF_8.toString())
            val destination = URLDecoder.decode(encodedDestination, StandardCharsets.UTF_8.toString())
            
            LoadingSecurityScreen(
                message = message,
                destinationRoute = destination,
                onNavigateToDestination = { route ->
                    // Obtener la ruta anterior en el momento de la navegación (no durante la composición)
                    val previousRoute = navController.previousBackStackEntry?.destination?.route
                    
                    // Navegar a la pantalla destino después de la animación
                    // Limpiar el back stack correctamente para evitar múltiples entradas
                    navController.navigate(route) {
                        // Usar launchSingleTop para evitar duplicados de la misma pantalla
                        launchSingleTop = true
                        // Limpiar hasta la pantalla anterior a LoadingSecurityScreen (ChangePin, FingerprintView, FingerprintAdd)
                        // inclusive = true para eliminar también esa pantalla del stack
                        // Esto asegura que ChangePinScreen, FingerprintViewScreen, FingerprintAddScreen y LoadingSecurityScreen
                        // sean eliminadas del back stack, dejando solo las pantallas anteriores
                        if (previousRoute != null) {
                            popUpTo(previousRoute) { inclusive = true }
                        } else {
                            // Si no hay ruta anterior, limpiar hasta el inicio del grafo
                            popUpTo(0) { inclusive = false }
                        }
                    }
                }
            )
        }
        
        // Loading Security Screen alternativa con popUpTo explícito (opcional)
        composable(
            route = "${Screen.LoadingSecurity.route}/{message}/{destination}/{popUpTo}",
            arguments = listOf(
                navArgument("message") { type = NavType.StringType },
                navArgument("destination") { type = NavType.StringType },
                navArgument("popUpTo") { 
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val encodedMessage = backStackEntry.arguments?.getString("message") ?: ""
            val encodedDestination = backStackEntry.arguments?.getString("destination") ?: ""
            val encodedPopUpTo = backStackEntry.arguments?.getString("popUpTo")
            val message = URLDecoder.decode(encodedMessage, StandardCharsets.UTF_8.toString())
            val destination = URLDecoder.decode(encodedDestination, StandardCharsets.UTF_8.toString())
            val popUpToRoute = encodedPopUpTo?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) }
            
            LoadingSecurityScreen(
                message = message,
                destinationRoute = destination,
                onNavigateToDestination = { route ->
                    // Navegar a la pantalla destino después de la animación
                    if (popUpToRoute != null) {
                        navController.navigate(route) {
                            // Limpiar el back stack hasta la ruta especificada
                            popUpTo(popUpToRoute) { inclusive = true }
                        }
                    } else {
                        navController.navigate(route)
                    }
                }
            )
        }

        // Terms And Conditions Screen
        composable(Screen.TermsAndConditions.route) {
            TermsAndConditionsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Profile Settings Screen
        composable(Screen.ProfileSettings.route) {
            ProfileSettingsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNotificationSettings = { navController.navigate(Screen.ProfileNotificationSettings.route) },
                onPasswordSettings = { navController.navigate(Screen.ProfilePasswordSettings.route) },
                onDeleteAccount = { navController.navigate(Screen.DeleteAccount.route) }
            )
        }

        // Profile Notification Settings Screen
        composable(Screen.ProfileNotificationSettings.route) {
            ProfileNotificationSettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Profile Password Settings Screen
        composable(Screen.ProfilePasswordSettings.route) {
            ProfilePasswordSettingsScreen(
                onNavigateBack = { navController.popBackStack() },
                onChangePasswordSuccess = { message ->
                    val encodedMessage = URLEncoder.encode(message, "UTF-8")
                    val encodedDestination = URLEncoder.encode(Screen.ProfileSettings.route, "UTF-8")
                    navController.navigate("${Screen.LoadingSecurity.route}/$encodedMessage/$encodedDestination")
                }
            )
        }

        // Delete Account Screen
        composable(Screen.DeleteAccount.route) {
            DeleteAccountScreen(
                onNavigateBack = { navController.popBackStack() },
                onDeleteAccount = { /* TODO: Implementar acción de eliminar cuenta */ },
                onCancel = { navController.popBackStack() }
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

