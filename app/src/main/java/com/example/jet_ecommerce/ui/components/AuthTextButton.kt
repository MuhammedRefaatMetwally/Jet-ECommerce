package com.example.jet_ecommerce.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextButton(
    text: String,
    onClick: () -> Unit,
    fontWeight: FontWeight,
    fontSize: Int,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
) {
    TextButton(

        modifier = modifier,
        onClick = { onClick() }) {

        Text(

            text = text,
            style = TextStyle(
                fontWeight = fontWeight,
                fontSize = fontSize.sp,
                color = Color.White
            )
        )

    }

}