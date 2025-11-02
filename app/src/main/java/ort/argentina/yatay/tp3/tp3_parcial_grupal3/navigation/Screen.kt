package ort.argentina.yatay.tp3.tp3_parcial_grupal3.navigation

/**
 * NAVEGACIÓN - Sealed class para definir las pantallas de la app
 */
sealed class Screen(val route: String) {
    // Auth Flow
    object Launch : Screen("launch")
    object OnBoarding : Screen("onboarding")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object ForgotPassword : Screen("forgot_password")

    // Main Flow
    object Home : Screen("home")
    object AccountBalance : Screen("account_balance")
    object Transaction : Screen("transaction")
    object Categories : Screen("categories")
    object Profile : Screen("profile")
    object Notifications : Screen("notifications")

    // Notifications (Dialog - no tiene ruta de navegación)

    companion object {
        fun fromRoute(route: String?): Screen = when (route) {
            Launch.route -> Launch
            OnBoarding.route -> OnBoarding
            Login.route -> Login
            SignUp.route -> SignUp
            ForgotPassword.route -> ForgotPassword
            Home.route -> Home
            AccountBalance.route -> AccountBalance
            Transaction.route -> Transaction
            Categories.route -> Categories
            Profile.route -> Profile
            Notifications.route -> Notifications
            else -> Launch
        }
    }
}

