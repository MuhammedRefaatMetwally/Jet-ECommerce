package com.example.jet_ecommerce.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.features.category.model.Category

@Composable
fun HomeCategoriesGrid(categories: List<Category?>?, span: ((Int) -> StaggeredGridItemSpan)) {
    CustomTitle(title = "Categories")
    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(count = 2),
        horizontalItemSpacing = 16.dp,
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp),
        content = {
            items(count = categories?.size ?: 0, span = span) { index ->
                CategoryWidget(
                    imageUrl = categories?.get(index)?.image ?: "",
                    categoryTitle = categories?.get(index)?.name ?: ""
                )


            }
        })
}