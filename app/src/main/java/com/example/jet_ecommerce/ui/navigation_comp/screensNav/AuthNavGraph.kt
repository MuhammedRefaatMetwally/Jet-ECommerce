package com.example.jet_ecommerce.ui.navigation_comp.screensNav
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.jet_ecommerce.ui.features.auth.login.LoginScreen
import com.example.jet_ecommerce.ui.features.main.MainScreen
import com.example.jet_ecommerce.ui.features.auth.register.RegisterScreen
import com.example.jet_ecommerce.ui.features.splash.SplashScreen
fun NavGraphBuilder.authNavGraph(navController: NavHostController){
navigation(
     route= Graph.AUTH,
    startDestination = ECommerceScreens.SplashScreen.name
){
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



object Graph{
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN =  "main_graph"
}