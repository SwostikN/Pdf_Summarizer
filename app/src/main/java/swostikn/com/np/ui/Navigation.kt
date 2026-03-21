package swostikn.com.np.ui

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object About : Screen("about")
    object History : Screen("history")
    object Settings : Screen("settings")
}
