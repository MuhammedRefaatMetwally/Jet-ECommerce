package com.example.jet_ecommerce.ui.features.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jet_ecommerce.ui.components.CustomTopBar
import com.example.jet_ecommerce.ui.components.PageView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jet_ecommerce.ui.features.main.home.renderStatesComponents.RenderCategoriesStates
import com.example.jet_ecommerce.ui.features.main.home.renderStatesComponents.RenderProductStates


@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    viewModel.invokeCategoriesAction(HomeContract.Action.LoadCategories)
    viewModel.invokeProductsAction(ProductContract.Action.LoadProducts)
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 8.dp)
            .verticalScroll(scrollState, enabled = true)
    ) {
        CustomTopBar()
        PageView()
        Spacer(modifier = Modifier.height(8.dp))

        RenderCategoriesStates(navController = navController)
        RenderProductStates(navController = navController)

        Spacer(modifier = Modifier.height(300.dp))


    }
}



