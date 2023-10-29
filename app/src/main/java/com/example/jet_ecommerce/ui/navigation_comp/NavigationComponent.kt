package com.example.jet_ecommerce.ui.navigation_comp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jet_ecommerce.ui.features.categories.CategoriesScreen
import com.example.jet_ecommerce.ui.features.home.HomeScreen
import com.example.jet_ecommerce.ui.features.login.LoginScreen
import com.example.jet_ecommerce.ui.features.profile.ProfileScreen
import com.example.jet_ecommerce.ui.features.register.RegisterScreen
import com.example.jet_ecommerce.ui.features.splash.SplashScreen
import com.example.jet_ecommerce.ui.features.wishlist.WishListScreen

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

        composable(ECommerceScreens.HomeScreen.name) {

           HomeScreen(navController = navController)
        }

        composable(ECommerceScreens.CategoriesScreen.name) {

            CategoriesScreen(navController = navController)
        }
        composable(ECommerceScreens.WishListScreen.name) {

            WishListScreen(navController = navController)
        }
        composable(ECommerceScreens.ProfileScreen.name) {

            ProfileScreen(navController = navController)
        }
    }
}