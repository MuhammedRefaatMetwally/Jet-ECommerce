package com.example.jet_ecommerce.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jet_ecommerce.R

@Composable
fun FavoriteItem(
    onAddToWishListClick: () -> Unit ={},
    isNewInWishList : Boolean = false,
    onRemoveFromWishListClick: () -> Unit
) {
    val isInWishList = remember {
        mutableStateOf(false)
    }
    isInWishList.value = isNewInWishList
    Card(shape = CircleShape, elevation = 8.dp, modifier = Modifier
        .padding(8.dp)
        .clickable {
            isInWishList.value = !isInWishList.value
            if (!isInWishList.value) {
                onAddToWishListClick()

            } else {
                onRemoveFromWishListClick()
            }

        }) {
        Icon(
            modifier = Modifier.padding(8.dp),
            painter = painterResource(id = if (isInWishList.value) R.drawable.active_heart else R.drawable.unactive_heart),
            contentDescription = "favorite",
            tint = Color.Unspecified
        )
    }

}