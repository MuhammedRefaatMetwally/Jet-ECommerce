package com.example.jet_ecommerce.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jet_ecommerce.R

@Composable
fun CustomTopBar() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Image(
            painter = painterResource(id = R.drawable.route_logo),
            modifier = Modifier.padding(8.dp),
            contentDescription = "logo"
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            SearchField(placeholderText = "what do you search for?")
            Image(
                painter = painterResource(id = R.drawable.__icon__shopping_cart_),
                modifier = Modifier.padding(top = 28.dp).size(30.dp),
                contentDescription = "cart"
            )
        }

    }
}