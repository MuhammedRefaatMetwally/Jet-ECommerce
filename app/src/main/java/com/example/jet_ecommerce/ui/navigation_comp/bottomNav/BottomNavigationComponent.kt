package com.example.jet_ecommerce.ui.navigation_comp.bottomNav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.jet_ecommerce.ui.features.main.categories.CategoriesScreen
import com.example.jet_ecommerce.ui.features.main.categories.CategoriesViewModel
import com.example.jet_ecommerce.ui.features.main.home.HomeScreen
import com.example.jet_ecommerce.ui.features.main.products.ProductsListScreen
import com.example.jet_ecommerce.ui.features.main.products.ProductsViewModel
import com.example.jet_ecommerce.ui.features.main.profile.ProfileScreen
import com.example.jet_ecommerce.ui.features.main.wishlist.WishListScreen
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens

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

        navigation(
            route = ECommerceScreens.NestedCategory.name,
            startDestination = BottomNavItem.Categories.screen_route
        ) {
            composable(BottomNavItem.Categories.screen_route) {
                val vm: CategoriesViewModel = hiltViewModel()
                CategoriesScreen(vm, navController = navController)
            }
            composable(
                "${ECommerceScreens.ProductsScreen.name}/{category_id}",
                arguments = listOf(navArgument("category_id") {
                    type = NavType.StringType
                })
            ) {
                val vm: ProductsViewModel = hiltViewModel()
                ProductsListScreen(vm = vm, navController = navController)
            }
            composable(ECommerceScreens.ProductDetailsScreen.name) {
//                val vm : ProductDetailsViewModel = hiltViewModel()
//                ProductDetailsScreen(vm = vm, navController =navController )
            }
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