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
    object ProfileEdit : Screen("profile_edit")
    object Security : Screen("security")
    object Fingerprint : Screen("fingerprint")
    object FingerprintView : Screen("fingerprint_view")
    object FingerprintAdd : Screen("fingerprint_add")
    object ChangePin : Screen("change_pin")
    object TermsAndConditions : Screen("terms_and_conditions")
    object LoadingSecurity : Screen("loading_security")
    object ProfileSettings : Screen("profile_settings")
    object ProfileNotificationSettings : Screen("profile_notification_settings")
    object ProfilePasswordSettings : Screen("profile_password_settings")
    object DeleteAccount : Screen("delete_account")

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
            ProfileEdit.route -> ProfileEdit
            Security.route -> Security
            Fingerprint.route -> Fingerprint
            FingerprintView.route -> FingerprintView
            FingerprintAdd.route -> FingerprintAdd
            ChangePin.route -> ChangePin
            TermsAndConditions.route -> TermsAndConditions
            LoadingSecurity.route -> LoadingSecurity
            ProfileSettings.route -> ProfileSettings
            ProfileNotificationSettings.route -> ProfileNotificationSettings
            ProfilePasswordSettings.route -> ProfilePasswordSettings
            DeleteAccount.route -> DeleteAccount
            else -> Launch
        }
    }
}

