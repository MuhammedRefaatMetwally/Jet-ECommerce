package com.example.jet_ecommerce.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jet_ecommerce.R

@Composable

fun SelectedNavIcon(@DrawableRes iconId: Int,descName : String) {
    Surface(Modifier.size(44.dp),color = Color.White, shape = CircleShape) {
        Icon(painterResource(id =iconId),modifier = Modifier.padding(8.dp), tint = colorResource(id = R.color.main_color), contentDescription = descName)
    }
}