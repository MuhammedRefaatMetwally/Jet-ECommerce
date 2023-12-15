package com.example.jet_ecommerce.ui.features.main

import com.example.jet_ecommerce.ui.navigation_comp.bottomNav.BottomNavigationBar
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jet_ecommerce.ui.navigation_comp.bottomNav.MainGraph
import com.example.jet_ecommerce.ui.navigation_comp.bottomNav.RootNavigationGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController  = rememberNavController()) {
    Scaffold(bottomBar = { BottomNavigationBar(navController = navHostController) }) {
        MainGraph(navHostController = navHostController)
    }
}