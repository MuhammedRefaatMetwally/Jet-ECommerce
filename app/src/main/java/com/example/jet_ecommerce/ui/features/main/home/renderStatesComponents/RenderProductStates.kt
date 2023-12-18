package com.example.jet_ecommerce.ui.features.main.home.renderStatesComponents

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.example.jet_ecommerce.R
import com.example.jet_ecommerce.ui.components.CustomAlertDialog
import com.example.jet_ecommerce.ui.components.CustomLoadingWidget
import com.example.jet_ecommerce.ui.components.HomeProductsLazyRow
import com.example.jet_ecommerce.ui.features.main.home.HomeViewModel
import com.example.jet_ecommerce.ui.features.main.home.ProductContract
import com.example.jet_ecommerce.ui.features.main.wishlist.WishListViewModel

@Composable
fun RenderProductStates(
    viewModel: HomeViewModel = hiltViewModel(), navController: NavHostController,
    wishListViewModel: WishListViewModel = hiltViewModel()
) {

    val states = produceState<ProductContract.State>(initialValue = ProductContract.State.Idle) {
        viewModel.productsStates.collect {
            value = it
        }
    }.value

    when (states) {

        is ProductContract.State.Error -> {
            var showDialog by remember { mutableStateOf(true) }
            CustomAlertDialog(dialogTitle = "Ops! Error",showDialog = showDialog,
                dialogDescription = states.message,
                onConfirm = {
//                    focusRequester.requestFocus()
                    showDialog = false
                },
                onDismiss = {
                    showDialog = false
                })
        }

        is ProductContract.State.Idle -> {
            Text(text = "IDLE")
        }

        is ProductContract.State.Loading -> {
            CustomLoadingWidget()
        }


        is ProductContract.State.ProductSuccess -> {

            val womenProducts = states.products?.filter { product ->
                product?.category?.name == "Women's Fashion"
            }
            val menProducts = states.products?.filter { product ->
                product?.category?.name == "Men's Fashion"
            }

            viewModel.menProducts = menProducts

            val eloProducts = states.products?.filter { product ->
                product?.category?.name == "Electronics"
            }

            HomeProductsLazyRow(womenProducts)
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.addiads_ads),
                contentScale = ContentScale.FillWidth,
                contentDescription = "ads"
            )
            HomeProductsLazyRow(menProducts)
            HomeProductsLazyRow(eloProducts)

        }
    }/* when (viewModel.events.value) {
         is HomeContract.Event.Idle -> {

         }

         is HomeContract.Event.NavigateToArticleDetails -> {

         }
     }*/
}