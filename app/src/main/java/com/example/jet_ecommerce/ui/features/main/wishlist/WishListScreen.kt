package com.example.jet_ecommerce.ui.features.main.wishlist

import ErrorToast
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.data.api.AppSharedReferences
import com.example.domain.features.products.model.Product
import com.example.jet_ecommerce.ui.components.CustomLoadingWidget
import com.example.jet_ecommerce.ui.components.wish_list_component.WishListItem
import com.example.jet_ecommerce.ui.features.auth.TokenViewModel
import com.example.jet_ecommerce.ui.features.main.carts.CartContract
import com.example.jet_ecommerce.ui.features.main.carts.CartViewModel
import com.example.jet_ecommerce.ui.features.main.home.RenderCustomTopBar
import com.example.jet_ecommerce.ui.features.main.products.ProductsViewModel
import com.example.jet_ecommerce.ui.features.main.products.productDetails.ProductDetailsContract
import com.example.jet_ecommerce.ui.features.main.products.productDetails.ProductDetailsViewModel


@Composable
fun WishListScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel(),
    tokenViewModel: TokenViewModel = hiltViewModel(),
    wishListViewModel: WishListViewModel = hiltViewModel()
) {
    cartViewModel.invokeAction(CartContract.Action.GetUserProducts)
    wishListViewModel.invokeAction(WishListContract.Action.GetWishListProducts)
    Column(modifier = Modifier.fillMaxSize()) {
        RenderCustomTopBar(
            cartViewModel = cartViewModel,
            tokenViewModel = tokenViewModel,
            navController = navController,
            isMainScreen = false
        )


        RenderWishListStates()
    }


}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RenderWishListStates(
    viewModel: WishListViewModel = hiltViewModel(),
    productDetailsViewModel: ProductDetailsViewModel = hiltViewModel(),
    productsViewModel: ProductsViewModel = hiltViewModel(),

    ) {
    val states = produceState<WishListContract.State>(initialValue = WishListContract.State.Idle) {
        viewModel.states.collect {
            value = it
        }
    }.value

    val events =
        produceState<WishListContract.Event>(initialValue = WishListContract.Event.Idle) {
            viewModel.eventsFlow.collect {
                value = it
            }
        }.value

    when (states) {
        is WishListContract.State.Error -> {
            ErrorToast(message = states.message)
        }

        is WishListContract.State.Idle -> {
        }

        is WishListContract.State.Loading -> CustomLoadingWidget()
        is WishListContract.State.Success -> {
            val wishListProduct = states.productData
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 48.dp)
            ) {
                items(wishListProduct) { product ->
                    //
                    WishListItem(isNewToWishList =true,
                        imgUrl = product.imageCover ?: "",
                        productName = product.title ?: "",
                        productPrice = product.price ?: 0,
                        onRemoveFromWishLis = {
                            viewModel.invokeAction(
                                WishListContract.Action.RemoveProductFomWishList(
                                    product.id ?: ""
                                )
                            )
                        }) {
                        productDetailsViewModel.invokeAction(
                            ProductDetailsContract.Action.AddProductToCart(
                                product.id ?: "",
                                1
                            )
                        )
                    }
                }
            }
        }
    }
}
