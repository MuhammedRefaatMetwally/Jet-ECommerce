package com.example.jet_ecommerce.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.jet_ecommerce.R


@Composable

fun ProductItem(
    modifier: Modifier = Modifier,
    imageURL: String,
    productTitle: String,
    price: Int,
    review: Double,
    isInWishList: Boolean = false,
    onItemClick: () -> Unit = {},
    onAddToCartClick: () -> Unit = {},
    onAddToWishListClick: () -> Unit = {},
    onRemoveFromWishListClick : () -> Unit ={}
) {
    Card(
        modifier = modifier
            .width(160.dp)
            .height(200.dp)
            .clickable { onItemClick() },
        shape = RoundedCornerShape(CornerSize(16.dp))
    ) {
        Column(
            modifier = Modifier.border(
                width = 2.dp,
                color = Color(0x4D004182),
                shape = RoundedCornerShape(size = 15.dp)
            ),
        ) {
            Box(contentAlignment = Alignment.TopEnd) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageURL),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(124.dp),
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.TopCenter,
                    contentDescription = "img"
                )
                FavoriteItem( onAddToWishListClick =  onAddToWishListClick) {
                   onRemoveFromWishListClick()
                }
            }

            Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                text = productTitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(
                    id = R.color.main_color
                )
            )
            Row() {
                Text(
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                    text = "Review ($review)",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.main_color),
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    modifier = Modifier
                        .graphicsLayer(alpha = 0.99f)
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                                drawRect(
                                    Brush.sweepGradient(
                                        colors = listOf(
                                            Color(0xFFF4B400),
                                            Color(0xFFFDD835),
                                            Color(0xFFFFFF8D)

                                        )
                                    ), blendMode = BlendMode.SrcAtop
                                )
                            }
                        },
                    contentDescription = "star"
                )

            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "EGP $price",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.main_color),
                    fontWeight = FontWeight.Medium

                )

                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 8.dp, bottom = 8.dp)
                        .clickable { onAddToCartClick() },
                    painter = painterResource(id = R.drawable.__icon__plus_circle_),
                    tint = colorResource(id = R.color.main_color),
                    contentDescription = "plus"
                )
            }


        }
    }

}


