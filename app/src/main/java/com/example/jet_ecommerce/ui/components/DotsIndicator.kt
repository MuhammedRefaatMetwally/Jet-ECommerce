package com.example.jet_ecommerce.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jet_ecommerce.R

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    secondStyle: Boolean = false,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
    onDotClick: (Int) -> Unit,
) {

    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(bottom = 24.dp)

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                if (secondStyle) {
                    Box(
                        modifier = Modifier
                            .width(30.dp)
                            .height(7.dp)
                            .clip(RoundedCornerShape(CornerSize(30.dp)))
                            .background(selectedColor)

                    )

                } else {
                    Box(
                        modifier = modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(selectedColor)

                    )
                }


            } else {
                Box(
                    modifier = modifier
                        .size(
                            if (secondStyle) {
                                7.dp
                            } else {
                                10.dp
                            }

                        )
                        .clip(CircleShape)
                        .background(
                            if (secondStyle) {
                                Color.White
                            } else {
                                unSelectedColor
                            }
                        )
                        .clickable {
                            onDotClick.invoke(index)
                        }
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

