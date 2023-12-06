package com.example.jet_ecommerce.ui.components.cart_component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jet_ecommerce.R


@Composable
fun QuantityButton(
    quantityValue: String,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit
) {
    Row(
        Modifier
            .width(122.dp)
            .height(42.dp)
            .background(color = Color(0xFF004182), shape = RoundedCornerShape(size = 20.dp))
            .padding(start = 16.dp, top = 11.dp, end = 16.dp, bottom = 11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.clickable {
                onMinusClick()
            },
            painter = painterResource(id = R.drawable.__icon__subtract_circle_minus_remove_),
            contentDescription = "Subtract icon",
            tint = Color.Unspecified
        )
        Text(
            text = quantityValue,
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            )
        )
        Icon(
            modifier = Modifier.clickable {
                onPlusClick()
            },
            painter = painterResource(id = R.drawable.__icon__plus_circle_),
            contentDescription = "plus icon",
            tint = Color.Unspecified
        )

    }
}