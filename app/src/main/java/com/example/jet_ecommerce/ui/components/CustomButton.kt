package com.example.jet_ecommerce.ui.components

import androidx.annotation.ColorRes
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
fun CustomButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    @ColorRes btnColor: Int = R.color.white,
    @ColorRes textColor: Int,
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(CornerSize(16.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = btnColor)),
        onClick = onClick
    ) {
        Text(text = title, color = colorResource(textColor))
    }
}
