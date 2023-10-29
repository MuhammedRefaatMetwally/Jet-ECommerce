package com.example.jet_ecommerce.ui.navigation_comp.bottomNav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jet_ecommerce.ui.features.categories.CategoriesScreen
import com.example.jet_ecommerce.ui.features.home.HomeScreen
import com.example.jet_ecommerce.ui.features.profile.ProfileScreen
import com.example.jet_ecommerce.ui.features.wishlist.WishListScreen

@Composable
fun ECommerceBottomNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screen_route
    ) {

        composable(BottomNavItem.Home.screen_route) {
          //  val viewModel = SplashViewModel()
            HomeScreen(navController = navController)
        }

        composable(BottomNavItem.Categories.screen_route) {
          //  val viewModel = LoginViewModel()
            CategoriesScreen(navController = navController)
        }

        composable(BottomNavItem.WishList.screen_route) {
            //val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            WishListScreen(navController = navController)
        }

        composable(BottomNavItem.Profile.screen_route) {

           ProfileScreen(navController = navController)
        }

    }
}