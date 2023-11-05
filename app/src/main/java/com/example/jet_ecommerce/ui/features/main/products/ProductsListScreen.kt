package com.example.jet_ecommerce.ui.features.main.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.domain.features.product.model.Product
import com.example.jet_ecommerce.ui.components.CustomAlertDialog
import com.example.jet_ecommerce.ui.components.CustomLoadingWidget
import com.example.jet_ecommerce.ui.components.CustomTopBar
import com.example.jet_ecommerce.ui.components.ProductItem

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
            (states as ProductsContract.States.Success).productsList
        )
    }
    when (events) {
        is ProductsContract.Events.Idle -> {}
        is ProductsContract.Events.NavigateToProductDetailsScreen -> {
            //navigate to productDetailsScreen
        }
    }

}


@Composable
fun ProductsListScreen(vm: ProductsViewModel, navController: NavHostController) {
    RenderViewState(vm, navController)
}

@Composable
fun ProductsContent(productsList: List<Product?>?) {

    Column(verticalArrangement = Arrangement.SpaceBetween) {
        CustomTopBar()
        ProductsVerticalGrid(productsList, Modifier.padding(8.dp))
    }
}

@Composable
fun ProductsVerticalGrid(productsList: List<Product?>?, modifier: Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {
        items(productsList?.size ?: 0) { index ->
            val item = productsList?.get(index)
            ProductItem(
                modifier =modifier,
                imageURL = item?.images?.get(0) ?: "",
                productTitle = item?.title ?: "",
                price = item?.price ?: 0,
                review = item?.ratingsAverage ?: 0.0
            )
        }
    }
}
