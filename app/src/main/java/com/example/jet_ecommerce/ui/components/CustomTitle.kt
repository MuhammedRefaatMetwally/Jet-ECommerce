package com.example.jet_ecommerce.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jet_ecommerce.R

@Composable
fun CustomTitle(title:String) {
    Text(
        text = title, maxLines = 1, overflow = TextOverflow.Ellipsis,style = TextStyle(
            color = colorResource(id = R.color.main_color),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,

        ), modifier = Modifier.padding(start = 8.dp)
    )
}