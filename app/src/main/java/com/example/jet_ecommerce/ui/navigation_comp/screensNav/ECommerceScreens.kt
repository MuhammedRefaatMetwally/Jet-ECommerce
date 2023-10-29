package com.example.jet_ecommerce.ui.navigation_comp.screensNav

enum class ECommerceScreens {
    SplashScreen,
    LoginScreen,
    RegisterScreen,
    MainScreen;

    companion object {
        fun fromRoute(route: String?): ECommerceScreens
        = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            RegisterScreen.name -> RegisterScreen
            MainScreen.name -> MainScreen
            null -> MainScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}