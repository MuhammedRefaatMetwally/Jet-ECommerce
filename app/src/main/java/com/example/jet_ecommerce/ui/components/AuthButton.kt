package com.example.jet_ecommerce.ui.components

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.jet_ecommerce.R

@Composable

fun CustomButton(modifier: Modifier=Modifier,title : String, onClick : ()-> Unit , @ColorRes colorId: Int = R.color.white) {
    Button(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(CornerSize(16.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = colorId)),
        onClick = onClick) {
        Text(text = title, color = colorResource(id = R.color.main_color))
    }
}