package com.example.jet_ecommerce.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.domain.features.products.model.Product
import com.example.jet_ecommerce.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeProductsLazyRow(products: List<Product?>?) {
    CustomTitle(title = products?.get(0)?.title ?: "")
    val state = rememberLazyListState()
    val showScrollToTopButton by remember {
        derivedStateOf {
            state.firstVisibleItemIndex > 0 // to save the state cuz of multi compositions
        }
    }
    val scope = rememberCoroutineScope()
    /*  remember {
          derivedStateOf {
              state.layoutInfo.visibleItemsInfo.map {
                  if(it.index == 2) it.wait() else it.notify()
              }
          }
      }*/
 /*   val pager = remember {
        Pager(
            PagingConfig(
                pageSize = products?.size ?: 0,
                enablePlaceholders = true,
                maxSize = 200
            )
        ) {
      viewModel.invokeProductsAction()
        }
    }
  *//*  fun getCourses(primaryCategory: String): Flow<PagingData<CourseData>> =
        courseDataRepository.getCourses(primaryCategory).cachedIn(viewModelScope)*/
    LazyRow(
        content = {
            items(products ?: listOf(), key = { it?.id ?: "" }) {

                Row(Modifier.animateItemPlacement(tween(250))) {
                    ProductItem(
                        imageURL = it?.images?.get(0) ?: "",
                        productTitle = it?.title ?: "",
                        price = it?.price ?: 0,
                        review = it?.ratingsAverage ?: 0.0
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                }

            }

            items(products?.size ?: 0) {
                AnimatedVisibility(visible = showScrollToTopButton && products?.lastIndex == it) {
                    Button(colors = ButtonDefaults.buttonColors(
                        colorResource(id = R.color.main_color)
                    ), onClick = {
                        scope.launch { state.animateScrollToItem(index = 0) }

                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            tint = Color.White,
                            contentDescription = ""
                        )
                    }
                }

            }
        },
        contentPadding = PaddingValues(16.dp),
        state = state,
        verticalAlignment = Alignment.CenterVertically
    )
}