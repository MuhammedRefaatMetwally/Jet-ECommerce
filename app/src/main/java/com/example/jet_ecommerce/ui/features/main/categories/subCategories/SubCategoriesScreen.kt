package com.example.jet_ecommerce.ui.features.categories.subCategories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController


@Composable
fun SubCategoriesScreen(vm: SubCategoryViewModel, navController: NavHostController) {
    val states by vm.states.collectAsState()
    LaunchedEffect(states) {
        when (states) {
            is SubCategoriesContract.State.Error -> TODO()
            is SubCategoriesContract.State.Loading -> TODO()
            is SubCategoriesContract.State.Success -> TODO()
        }
    }

}

@Composable
fun SubCategoryContent(categoryId: Int) {

}