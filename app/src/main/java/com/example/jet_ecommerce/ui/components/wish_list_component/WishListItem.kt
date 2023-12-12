package com.example.jet_ecommerce.ui.components.wish_list_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.jet_ecommerce.R
import com.example.jet_ecommerce.ui.components.FavoriteItem
import com.example.jet_ecommerce.ui.components.cart_component.PriceText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishListItem(
    imgUrl: String,
    productName: String,
    productPrice: Int,
    onRemoveFromWishLis: () -> Unit,
    isNewToWishList : Boolean = true,
    onAddToCartClick: () -> Unit,
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
                    Text(modifier = Modifier.fillMaxWidth(.7f),
                        text = productName,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 8.sp,
                            fontWeight = FontWeight(500),
                            color = colorResource(id = R.color.description_color_60op),
                        )
                    )

                    FavoriteItem(isNewInWishList = isNewToWishList) {
                        onRemoveFromWishLis()
                    }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    PriceText(modifier = Modifier.padding(top = 16.dp), price = productPrice.toString())

                    Button(modifier = Modifier
                        .width(270.dp)
                        .height(48.dp).padding(start = 8.dp),
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor =colorResource(id = R.color.main_color) ),
                        onClick = {
                            onAddToCartClick()
                    }) {
                                Text(modifier = Modifier.fillMaxWidth(),
                                    text = "Add To Cart",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFFFFFFFF),
                                        textAlign = TextAlign.Center,
                                    )
                                )
                    }
                }
            }


        }
    }
}
