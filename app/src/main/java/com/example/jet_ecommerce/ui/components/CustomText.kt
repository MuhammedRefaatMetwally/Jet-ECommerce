package com.example.jet_ecommerce.ui.components


import androidx.compose.ui.graphics.Color

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    color: Color,
) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = color,
            )
    )

}