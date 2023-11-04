package com.example.jet_ecommerce.ui.features.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jet_ecommerce.ui.components.CustomTopBar
import com.example.jet_ecommerce.ui.components.PageView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jet_ecommerce.ui.components.CustomTitle
import com.example.jet_ecommerce.ui.features.main.home.renderStatesComponents.RenderCategoriesStates
import com.example.jet_ecommerce.ui.features.main.home.renderStatesComponents.RenderProductStates


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    viewModel.invokeCategoriesAction(HomeContract.Action.LoadCategories)
    viewModel.invokeProductsAction(ProductContract.Action.LoadProducts)

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(start = 8.dp),
        content = {

            item {
                CustomTopBar()
            }

            item {
                PageView()
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }


            item {
                RenderCategoriesStates(navController = navController)
            }


            item {
                RenderProductStates(navController = navController)
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

    )


}



