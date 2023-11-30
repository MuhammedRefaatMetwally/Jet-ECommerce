package com.example.jet_ecommerce.ui.navigation_comp.screensNav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jet_ecommerce.ui.features.auth.login.LoginScreen
import com.example.jet_ecommerce.ui.features.main.MainScreen
import com.example.jet_ecommerce.ui.features.auth.register.RegisterScreen
import com.example.jet_ecommerce.ui.features.splash.SplashScreen

@Composable
fun ECommerceNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ECommerceScreens.SplashScreen.name
    ) {

        composable(ECommerceScreens.SplashScreen.name) {
            //  val viewModel = SplashViewModel()
            SplashScreen(navController = navController)
        }

        composable(ECommerceScreens.LoginScreen.name) {
            //  val viewModel = LoginViewModel()
            LoginScreen(navController = navController)
        }

        composable(ECommerceScreens.RegisterScreen.name) {
            //val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            RegisterScreen(navController = navController)
        }

        composable(ECommerceScreens.MainScreen.name) {
            MainScreen()
        }



    }
}