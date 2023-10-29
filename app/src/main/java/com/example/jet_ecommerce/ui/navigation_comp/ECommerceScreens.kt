package com.example.jet_ecommerce.ui.navigation_comp

enum class ECommerceScreens {
    SplashScreen,
    LoginScreen,
    RegisterScreen,
    HomeScreen,
    CategoriesScreen,
    WishListScreen,
    ProfileScreen;

    companion object {
        fun fromRoute(route: String?): ECommerceScreens
        = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            RegisterScreen.name -> RegisterScreen
            HomeScreen.name -> HomeScreen
            CategoriesScreen.name -> CategoriesScreen
            WishListScreen.name -> WishListScreen
            ProfileScreen.name -> ProfileScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}