package com.example.jet_ecommerce.ui.components.cart_component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.jet_ecommerce.R



@Composable
fun PriceText(modifier: Modifier = Modifier, price : String, lineHeight : TextUnit = 0.sp) {
    Text(text = buildAnnotatedString {
        withStyle(
            SpanStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight(500),
            color = colorResource(id = R.color.description_color_60op),
        )
        ){
            append("EGP")
            append(" ")
            append(price)
        }
    }, modifier = modifier, style = TextStyle(lineHeight = lineHeight))
}