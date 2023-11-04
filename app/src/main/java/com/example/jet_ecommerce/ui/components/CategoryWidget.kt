package com.example.jet_ecommerce.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.jet_ecommerce.R


@Composable

fun CategoryWidget(imageUrl: String, categoryTitle: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(100.dp)
                .testTag(tag = "circle"),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(imageUrl),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }
        Text(
            text = categoryTitle,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.main_color),
            fontSize = 18.sp,
            maxLines = 2,
            modifier = Modifier
                .padding(top = 8.dp)
                .width(120.dp)
        )
    }
}