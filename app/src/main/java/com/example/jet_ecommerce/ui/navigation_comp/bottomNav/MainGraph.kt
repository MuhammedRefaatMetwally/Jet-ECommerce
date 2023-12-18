package com.example.jet_ecommerce.ui.navigation_comp.bottomNav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jet_ecommerce.ui.features.auth.login.LoginScreen
import com.example.jet_ecommerce.ui.features.main.carts.CartScreen
import com.example.jet_ecommerce.ui.features.main.carts.CartViewModel
import com.example.jet_ecommerce.ui.features.main.categories.CategoriesScreen
import com.example.jet_ecommerce.ui.features.main.categories.CategoriesViewModel
import com.example.jet_ecommerce.ui.features.main.home.HomeScreen
import com.example.jet_ecommerce.ui.features.main.home.HomeViewModel
import com.example.jet_ecommerce.ui.features.main.products.ProductsListScreen
import com.example.jet_ecommerce.ui.features.main.products.ProductsViewModel
import com.example.jet_ecommerce.ui.features.main.products.productDetails.ProductDetailsScreen
import com.example.jet_ecommerce.ui.features.main.products.productDetails.ProductDetailsViewModel
import com.example.jet_ecommerce.ui.features.main.profile.ProfileScreen
import com.example.jet_ecommerce.ui.features.main.wishlist.WishListScreen
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.Graph

@Composable
fun MainGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.MAIN,
        startDestination = BottomNavItem.Home.screen_route
    ) {
        composable(BottomNavItem.Home.screen_route) {
            //  val viewModel = SplashViewModel()
            HomeScreen(navController = navHostController)
        }
        composable(BottomNavItem.Categories.screen_route) {
            val vm: CategoriesViewModel = hiltViewModel()
            CategoriesScreen(vm, navController = navHostController)
        }

        composable(
            route = "${ECommerceScreens.ProductsScreen.name}/{category_id}",
            arguments = listOf(navArgument("category_id") {
                type = NavType.StringType
            })
        ) {
            val vm: ProductsViewModel = hiltViewModel()
            ProductsListScreen(vm = vm, navController = navHostController)
        }
        composable(
            route = "${ECommerceScreens.ProductDetailsScreen.name}/{product_id}",
            arguments = listOf(navArgument("product_id") {
                type = NavType.StringType
            }),

        ) {
            val vm: ProductDetailsViewModel = hiltViewModel()

            ProductDetailsScreen(vm = vm, navController = navHostController)
        }

        composable(BottomNavItem.WishList.screen_route) {
            val cartViewModel = hiltViewModel<CartViewModel>()
            val viewModel = it.sharedViewModel<HomeViewModel>(navController = navHostController)
            WishListScreen(navController = navHostController,cartViewModel, homeViewModel =  viewModel)
        }

        composable(BottomNavItem.Profile.screen_route) {

            ProfileScreen(navController = navHostController)
        }

        composable(ECommerceScreens.CartScreen.name) {
            CartScreen(navHostController)
        }




        composable(ECommerceScreens.LoginScreen.name) {
            //  val viewModel = LoginViewModel()
            LoginScreen(navController = navHostController)
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavHostController) : T{
    val  navGraphRoute = destination.parent?.route ?: return  hiltViewModel()

    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return  hiltViewModel(parentEntry)
}