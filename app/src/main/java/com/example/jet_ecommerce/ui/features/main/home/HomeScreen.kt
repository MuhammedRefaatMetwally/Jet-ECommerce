package com.example.jet_ecommerce.ui.features.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jet_ecommerce.ui.components.CustomTopBar
import com.example.jet_ecommerce.ui.components.PageView
import com.example.jet_ecommerce.ui.features.auth.TokenViewModel
import com.example.jet_ecommerce.ui.features.main.home.renderStatesComponents.RenderCategoriesStates
import com.example.jet_ecommerce.ui.features.main.home.renderStatesComponents.RenderProductStates
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens


@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel(),tokenViewModel: TokenViewModel= hiltViewModel()) {
    viewModel.invokeCategoriesAction(HomeContract.Action.LoadCategories)
    viewModel.invokeProductsAction(ProductContract.Action.LoadProducts)

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(start = 8.dp),
        content = {

            item {
                CustomTopBar(modifier = Modifier.clickable {
                    tokenViewModel.deleteToken()
                    navController.popBackStack()
                    navController.navigate(ECommerceScreens.LoginScreen.name)
                })
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

