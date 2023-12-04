package com.example.jet_ecommerce.ui.features.main.home.renderStatesComponents

import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jet_ecommerce.ui.components.CustomAlertDialog
import com.example.jet_ecommerce.ui.components.CustomLoadingWidget
import com.example.jet_ecommerce.ui.components.HomeCategoriesGrid
import com.example.jet_ecommerce.ui.features.main.home.HomeContract
import com.example.jet_ecommerce.ui.features.main.home.HomeViewModel

@Composable
fun RenderCategoriesStates(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val states = produceState<HomeContract.State>(initialValue = HomeContract.State.Idle) {
        viewModel.categoriesStates.collect {
            value = it
        }
    }.value

    when (states) {

        is HomeContract.State.Loading -> {
            CustomLoadingWidget()
        }

        is HomeContract.State.Error -> {
            var showDialog by remember { mutableStateOf(true) }
            CustomAlertDialog(dialogTitle = "Ops! Error",showDialog = showDialog, dialogDescription = states.message,
                onConfirm = {
//                    focusRequester.requestFocus()
                    showDialog = false
                },
                onDismiss = {
                    showDialog = false
                })
        }

        is HomeContract.State.Idle -> {
            Text(text = "IDLE")
        }

        is HomeContract.State.CategorySuccess -> {
            val categories = states.categories
            val span: (Int) -> StaggeredGridItemSpan = { StaggeredGridItemSpan.SingleLane }
            HomeCategoriesGrid(categories = categories, span = span)

        }

    }
    /* when (viewModel.events.value) {
         is HomeContract.Event.Idle -> {

         }

         is HomeContract.Event.NavigateToArticleDetails -> {

         }
     }*/
}