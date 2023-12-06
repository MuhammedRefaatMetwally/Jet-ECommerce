package com.example.jet_ecommerce.ui.features.main.carts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
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
import com.example.jet_ecommerce.ui.components.cart_component.CartItem
import com.example.jet_ecommerce.ui.components.toasts.InfoToast
import com.example.jet_ecommerce.ui.features.auth.TokenViewModel
import com.example.jet_ecommerce.ui.features.main.home.RenderCustomTopBar
import com.example.jet_ecommerce.ui.features.main.products.productDetails.ProductDetailsContract

@Composable
fun RenderCartStates(viewModel: CartViewModel = hiltViewModel()) {
    val states = produceState<CartContract.State>(initialValue = CartContract.State.Idle) {
        viewModel.states.collect {
            value = it
        }
    }.value



    when (states) {
        is CartContract.State.Error -> {

            InfoToast("No Items In Cart")

        }

        CartContract.State.Idle -> {}
        CartContract.State.Loading -> CustomLoadingWidget()
        is CartContract.State.Success -> {
            val cartProducts = states.data.products
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(cartProducts?.size ?: 0) { index ->
                    CartItem(
                        imgUrl = cartProducts?.get(index)?.product?.imageCover ?: "",
                        productName = cartProducts?.get(index)?.product?.title ?: "",
                        productPrice = cartProducts?.get(index)?.price ?: 0,
                        quantityValue = cartProducts?.get(index)?.product?.quantity ?: "",
                        onMinusClick = {

                        }) {

                    }
                }
            }

        }
    }

}

@Composable
fun CartScreen(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    tokenViewModel: TokenViewModel = hiltViewModel(),
) {
    viewModel.invokeAction(CartContract.Action.GetUserProducts)
    var showDialog by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {

        RenderCustomTopBar(
            nonDetailsTopBarTitle = "Carts",
            cartViewModel = cartViewModel,
            tokenViewModel = tokenViewModel,
            navController = navController,
            detailsTopBar = false,
            isMainScreen = false, onBackClick = { navController.popBackStack() },
            onLongCartClick = {
                showDialog = true
            }
        )
        if (showDialog){
            CustomAlertDialog(
                dialogTitle = "Are you Sure To Clear Your Cart?",
                showDialog =showDialog,
                dialogDescription ="clear cart",
                 onDismiss = {
                     showDialog = false
                 }
                ) {

               viewModel.invokeAction(CartContract.Action.ClearCart)
                showDialog = false
                navController.popBackStack()
            }
        }
        RenderCartStates(viewModel)
    }


}