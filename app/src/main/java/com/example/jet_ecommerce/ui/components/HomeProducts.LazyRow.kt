package com.example.jet_ecommerce.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.features.product.model.Product

@Composable
fun HomeProductsLazyRow(products : List<Product?>?) {
    CustomTitle(title = products?.get(0)?.title ?: "",)
    LazyRow(
        content = {
            items(products?.get(2)?.images?.size?.minus(1) ?:0) { index ->
                     ProductItem(
                         imageURL = products?.get( index)?.images?.get(if(index==3) index.minus(3)else index) ?: "",
                         productTitle = /*products?.get(index)?.title ?: */"s",
                         price =/* products?.get(index)?.price ?:*/ 0,
                         review = /*products?.get(index)?.ratingsAverage ?:*/ 0.0,
                     )
                     Spacer(modifier = Modifier.width(16.dp))



            }
        },
contentPadding = PaddingValues(16.dp)    )
}