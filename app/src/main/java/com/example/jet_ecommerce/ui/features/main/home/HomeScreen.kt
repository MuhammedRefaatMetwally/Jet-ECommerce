package com.example.jet_ecommerce.ui.features.main.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jet_ecommerce.ui.components.CustomAlertDialog
import com.example.jet_ecommerce.ui.components.CustomLoadingWidget
import com.example.jet_ecommerce.ui.components.CustomTopBar
import com.example.jet_ecommerce.ui.components.PageView
import com.example.jet_ecommerce.ui.features.auth.TokenViewModel
import com.example.jet_ecommerce.ui.features.main.carts.CartContract
import com.example.jet_ecommerce.ui.features.main.carts.CartViewModel
import com.example.jet_ecommerce.ui.features.main.home.renderStatesComponents.RenderCategoriesStates
import com.example.jet_ecommerce.ui.features.main.home.renderStatesComponents.RenderProductStates
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
    tokenViewModel: TokenViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    viewModel.invokeCategoriesAction(HomeContract.Action.LoadCategories)
    viewModel.invokeProductsAction(ProductContract.Action.LoadProducts)
    cartViewModel.invokeAction(CartContract.Action.GetUserProducts)


    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(start = 8.dp),
        content = {

            item {
                RenderCustomTopBar(cartViewModel, tokenViewModel, navController, isMainScreen = true)
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

@Composable
fun RenderCustomTopBar(
    cartViewModel: CartViewModel,
    tokenViewModel: TokenViewModel,
    navController: NavHostController,
    isMainScreen: Boolean = false,
    detailsTopBar: Boolean = true,
    nonDetailsTopBarTitle: String = "",
    onCartClick : () -> Unit = {},
    onLongCartClick : ()->Unit ={},
    onMainCartClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val states = produceState<CartContract.State>(initialValue = CartContract.State.Idle) {
        cartViewModel.states.collect {
            value = it
        }
    }.value
    when (states) {
        is CartContract.State.Error -> {
            CustomTopBar(detailsTopBar = detailsTopBar,
                nonDetailsTopBarTitle = nonDetailsTopBarTitle,
                cartItemsSize = cartViewModel.cartProductsSize ?: 0,
                isMainScreen = isMainScreen,
                modifier = Modifier.clickable {
                    tokenViewModel.deleteToken()
                    navController.navigate(ECommerceScreens.LoginScreen.name)
                }, onCartClick = {onCartClick()}, onBackClick = {onBackClick()}, onLongCartClick = onLongCartClick,
                onMainCartClick = { navController.navigate(ECommerceScreens.CartScreen.name) })
        }

        CartContract.State.Idle -> {}
        CartContract.State.Loading -> CustomLoadingWidget()
        is CartContract.State.Success -> {
            CustomTopBar(detailsTopBar = detailsTopBar,
                nonDetailsTopBarTitle = nonDetailsTopBarTitle,
                cartItemsSize = cartViewModel.cartProductsSize ?: 0,
                isMainScreen = isMainScreen,
                modifier = Modifier.clickable {
                    tokenViewModel.deleteToken()
                    navController.popBackStack()
                    navController.navigate(ECommerceScreens.LoginScreen.name)
                }, onCartClick = {onCartClick()}, onBackClick = {onBackClick()}, onLongCartClick = onLongCartClick,
                onMainCartClick = { navController.navigate(ECommerceScreens.CartScreen.name) })
        }
    }

}

