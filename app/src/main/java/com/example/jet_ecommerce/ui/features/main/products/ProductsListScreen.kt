package com.example.jet_ecommerce.ui.features.main.products

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.features.products.model.Product
import com.example.jet_ecommerce.ui.components.CustomAlertDialog
import com.example.jet_ecommerce.ui.components.CustomLoadingWidget
import com.example.jet_ecommerce.ui.components.CustomTopBar
import com.example.jet_ecommerce.ui.components.ProductItem
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens

@Composable
fun RenderViewState(vm: ProductsViewModel, navController: NavHostController) {
    val states by vm.states.collectAsState()
    val events by vm.events.collectAsState()

    when (states) {
        is ProductsContract.States.Error -> {
            var showDialog by remember { mutableStateOf(true) }
            (states as ProductsContract.States.Error).message?.let {
                CustomAlertDialog(showDialog = showDialog, dialogDescription = it,
                    onConfirm = { showDialog = false },
                    onDismiss = { showDialog = false }
                )
            }
        }

        is ProductsContract.States.Loading -> {
            CustomLoadingWidget()
        }

        is ProductsContract.States.Success -> ProductsContent(
            (states as ProductsContract.States.Success).productsList?.collectAsLazyPagingItems()!!,
            onItemClick = { vm.invokeAction(ProductsContract.Action.ProductClick(it)) },
            onAddToCartClick = { vm.invokeAction(ProductsContract.Action.AddToCartButtonClick(it.id)) },
            onAddToWishListClick = { vm.invokeAction(ProductsContract.Action.AddToWishListButtonClick(it.id)) }
        )
    }
    when (events) {
        is ProductsContract.Events.Idle -> {}
        is ProductsContract.Events.NavigateToProductDetailsScreen -> {
            //navigate to productDetailsScreen
            Log.e("Events", "RenderViewState: ")
            navController.navigate("${ECommerceScreens.ProductDetailsScreen.name}/${(events as ProductsContract.Events.NavigateToProductDetailsScreen).productId}")
        }
    }
}


@Composable
fun ProductsListScreen(vm: ProductsViewModel, navController: NavHostController) {
    RenderViewState(vm, navController)
}

@Composable
fun ProductsContent(
    productsLazyPagingItems: LazyPagingItems<Product>,
    onItemClick: (productId: String) -> Unit,
    onAddToCartClick: (item: Product) -> Unit,
    onAddToWishListClick: (item: Product) -> Unit
) {

    Column(verticalArrangement = Arrangement.SpaceBetween) {
        CustomTopBar()
        ProductsVerticalGrid(productsLazyPagingItems, Modifier.padding(8.dp),
            onItemClick = { onItemClick(it) },
            onAddToCartClick = { onAddToCartClick(it) },
            onAddToWishListClick = { onAddToWishListClick(it) })
    }
}

@Composable
fun ProductsVerticalGrid(
    productsLazyPagingItems: LazyPagingItems<Product>,
    modifier: Modifier,
    onItemClick: (productId: String) -> Unit,
    onAddToCartClick: (item: Product) -> Unit,
    onAddToWishListClick: (item: Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight(0.93f)
    ) {
        items(productsLazyPagingItems.itemCount) { index ->
            val item = productsLazyPagingItems[index]
            ProductItem(
                modifier = modifier,
                imageURL = item?.images?.get(0) ?: "",
                productTitle = item?.title ?: "",
                price = item?.price ?: 0,
                review = item?.ratingsAverage ?: 0.0,
                isInWishList = false,
                onItemClick = { onItemClick(item?.id!!) },
                onAddToCartClick = { onAddToCartClick(item!!) },
                onAddToWishListClick = { onAddToWishListClick(item!!) }
            )
        }

        productsLazyPagingItems.apply {

            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CustomLoadingWidget() }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = productsLazyPagingItems.loadState.refresh as LoadState.Error
                    item {
                        var showDialog by remember { mutableStateOf(true) }
                        CustomAlertDialog(showDialog = showDialog,
                            dialogDescription = error.error.localizedMessage!!,
                            onConfirm = {
                                retry()
                                showDialog = false
                            },
                            onDismiss = { showDialog = false }
                        )
                    }

                }

                loadState.append is LoadState.Loading -> {
                    item { CustomLoadingWidget() }
                }

                loadState.append is LoadState.Error -> {
                    val error = productsLazyPagingItems.loadState.append as LoadState.Error
                    item {
                        var showDialog by remember { mutableStateOf(true) }
                        CustomAlertDialog(showDialog = showDialog,
                            dialogDescription = error.error.localizedMessage!!,
                            onConfirm = {
                                retry()
                                showDialog = false
                            },
                            onDismiss = { showDialog = false }
                        )
                    }
                }
            }
        }


    }
}
