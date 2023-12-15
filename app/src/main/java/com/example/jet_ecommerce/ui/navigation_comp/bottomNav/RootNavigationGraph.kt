package com.example.jet_ecommerce.ui.navigation_comp.bottomNav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jet_ecommerce.ui.features.main.MainScreen
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.Graph
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.authNavGraph

@Composable
fun RootNavigationGraph(
navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        route=Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navHostController)
        composable(route = Graph.MAIN){
            MainScreen()
        }
    }
}