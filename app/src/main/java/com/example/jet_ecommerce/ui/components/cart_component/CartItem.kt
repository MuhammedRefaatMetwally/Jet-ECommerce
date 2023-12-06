package com.example.jet_ecommerce.ui.components.cart_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.jet_ecommerce.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItem(
    imgUrl: String,
    productName: String,
    productPrice: Int,
    quantityValue: Comparable<*>,
    onDeleteClick : () -> Unit,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        onClick = { }, modifier = Modifier
            .fillMaxWidth()
            .height(132.dp)
            .padding(8.dp)
            .border(
                BorderStroke(1.dp, color = colorResource(id = R.color.description_color_60op)),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row() {
            Card(
                modifier = Modifier,
                border = BorderStroke(
                    width = 1.dp,
                    color = colorResource(id = R.color.main_color),
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = imgUrl),
                    modifier = Modifier
                        .fillMaxWidth(.4f)
                        .height(124.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    contentDescription = "img"
                )
            }

            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(modifier = Modifier.fillMaxWidth(.9f),
                        text = productName,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(500),
                            color = colorResource(id = R.color.description_color_60op),
                        )
                    )

                    Image(modifier = Modifier.clickable {
                        onDeleteClick()
                    },
                        painter = painterResource(id = R.drawable.delete_icon),
                        contentDescription = "delete",
                        contentScale = ContentScale.None
                    )
                }
                /*Spacer(modifier = Modifier.height(48.dp))*/
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    PriceText(modifier = Modifier.padding(top = 16.dp), price = productPrice.toString())

                    QuantityButton(quantityValue = quantityValue.toString(), onMinusClick = { onMinusClick()}) {
                     onPlusClick()
                    }

                }
            }


        }
    }
}
