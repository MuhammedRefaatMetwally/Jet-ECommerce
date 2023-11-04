package com.example.jet_ecommerce.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jet_ecommerce.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PageView() {
    val slideImage = remember { mutableIntStateOf(R.drawable.home_page_1) }
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    HorizontalPager(modifier = Modifier.height(224.dp),count = 3, state = pagerState) { page ->
        when (page) {

            0 -> {
                slideImage.intValue = R.drawable.home_page_1
            }

            1 -> {
                slideImage.intValue = R.drawable.home_page_2
            }

            2 -> {
                slideImage.intValue = R.drawable.home_page_3
            }
        }

        Column() {

            Box(contentAlignment = Alignment.BottomCenter) {
                Image(
                    painterResource(slideImage.intValue),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(16.dp), contentScale = ContentScale.FillBounds,
                    contentDescription = ""
                )
                DotsIndicator(
                    totalDots = 3,
                    selectedIndex = pagerState.currentPage,
                    selectedColor = colorResource(id = R.color.main_color),
                    unSelectedColor = colorResource(id = R.color.white)
                ) {
                    scope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                }
                if (page==0 || page ==2) CustomButton(
                    title = "Shop Now",
                    modifier = Modifier.padding(end = 216.dp, bottom = 32.dp),
                    textColor =if(page==0) R.color.white else R.color.main_color,
                    btnColor = if(page==0) R.color.main_color else R.color.white,
                    onClick = { /*TODO*/ })
                else
                    CustomButton(
                        title = "Shop Now",
                        modifier = Modifier.padding(start = 172.dp, bottom = 48.dp),
                        textColor = R.color.white,
                        btnColor = R.color.main_color,
                        onClick = { /*TODO*/ })
            }

        }


    }
}