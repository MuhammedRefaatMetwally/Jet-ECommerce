package com.example.jet_ecommerce.ui.features.main.carts

import ErrorToast
import SuccessToast
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.features.cart.model.getLoggedUse.CartQuantity
import com.example.jet_ecommerce.ui.components.CustomAlertDialog
import com.example.jet_ecommerce.ui.components.CustomLoadingWidget
import com.example.jet_ecommerce.ui.components.cart_component.CartItem
import com.example.jet_ecommerce.ui.components.toasts.InfoToast
import com.example.jet_ecommerce.ui.features.auth.TokenViewModel
import com.example.jet_ecommerce.ui.features.main.home.RenderCustomTopBar

@SuppressLint("SuspiciousIndentation")
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
            val products = states.data
            ListOfCartProducts(products)
        }
    }

    val events =
        produceState<CartContract.Event>(initialValue = CartContract.Event.Idle) {
            viewModel.eventsFlow.collect {
                value = it
            }
        }.value

    when (events) {
        CartContract.Event.Idle -> {}
        CartContract.Event.ShowError -> {
            ErrorToast(message = "Something Went Wrong! Could Not Remove it.")
        }

        CartContract.Event.ShowSuccess -> {
            SuccessToast(message = "Product Removed Successfully")

        }
    }
}

@Composable
fun ListOfCartProducts(products : CartQuantity, viewModel: CartViewModel = hiltViewModel()) {
    val productCount = remember { mutableIntStateOf(1) }
    val productTotalPrice = remember { mutableIntStateOf(0) }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(products.products?.size ?: 0, key = { index -> index }) { index->
            productCount.intValue = products.products?.get(index)?.count ?:0
            productTotalPrice.intValue = products.totalCartPrice ?:0
            val productId = products.products?.get(index)?.product?.id ?: ""
            CartItem(
                imgUrl = products.products?.get(index)?.product?.imageCover ?: "",
                productName = products.products?.get(index)?.product?.title ?: "",
                productPrice = productTotalPrice.intValue,
                quantityValue = productCount.intValue,
                onDeleteClick = {
                    viewModel.invokeAction(
                        CartContract.Action.DeleteSpecificCartItem(
                            productId = productId,
                            product = products.products?.get(index)
                        )
                    )
                },
                onMinusClick = {
                    if (productCount.intValue > 1)
                        productCount.intValue--
                    viewModel.invokeAction(
                        CartContract.Action.UpdateCartProductQuantity(
                            count = productCount.intValue ,
                            productId
                        )
                    )
                    productTotalPrice.intValue = products.totalCartPrice ?:0
                }) {
                productCount.intValue++
                viewModel.invokeAction(
                    CartContract.Action.UpdateCartProductQuantity(
                        count = productCount.intValue,
                        productId
                    )
                )
                productTotalPrice.intValue = products.totalCartPrice ?:0
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
        if (showDialog) {
            CustomAlertDialog(
                dialogTitle = "Are you Sure To Clear Your Cart?",
                showDialog = showDialog,
                dialogDescription = "clear cart",
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