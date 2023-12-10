package com.example.jet_ecommerce.ui.features.main.products.productDetails

import ErrorToast
import SuccessToast
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.features.products.model.Product
import com.example.jet_ecommerce.R
import com.example.jet_ecommerce.ui.components.CustomAlertDialog
import com.example.jet_ecommerce.ui.components.CustomLoadingWidget
import com.example.jet_ecommerce.ui.features.auth.TokenViewModel
import com.example.jet_ecommerce.ui.features.main.carts.CartContract
import com.example.jet_ecommerce.ui.features.main.carts.CartViewModel
import com.example.jet_ecommerce.ui.features.main.home.RenderCustomTopBar
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay


@Composable
fun ProductDetailsScreen(vm: ProductDetailsViewModel, navController: NavHostController) {
    RenderViewState(vm, navController)
}

@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun RenderViewState(
    vm: ProductDetailsViewModel,
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel(),
) {
    val productDetailsStates by vm.productDetailsStates.collectAsState()
    val productDetailsEvents by vm.productDetailsEvents.collectAsState()
    val addToCartStates by vm.addToCartStates.collectAsState()
    cartViewModel.invokeAction(CartContract.Action.GetUserProducts)
    when (productDetailsStates) {
        is ProductDetailsContract.ProductDetailsStates.Loading -> {
            CustomLoadingWidget()
        }

        is ProductDetailsContract.ProductDetailsStates.Error -> {
            var showDialog by remember { mutableStateOf(true) }
            CustomAlertDialog(showDialog = showDialog,
                dialogTitle = "Error",
                dialogDescription = (productDetailsStates as ProductDetailsContract.ProductDetailsStates.Error).message,
                onDismiss = { showDialog = false },
                onConfirm = { showDialog = false })
        }

        is ProductDetailsContract.ProductDetailsStates.Success -> {
            val product =
                (productDetailsStates as ProductDetailsContract.ProductDetailsStates.Success).product
            ProductDetailsContent(
                navController = navController,
                product = product,
                onAddToCartClick = { quantity ->
                    vm.invokeAction(
                        ProductDetailsContract.Action.AddProductToCart(
                            product.id!!,
                            quantity
                        )
                    )
                },
                onAddToWishListClick = {
                    vm.invokeAction(ProductDetailsContract.Action.AddProductToWishList(product.id!!))
                })
        }

        else -> {}
    }


    val events =
        produceState<ProductDetailsContract.ProductDetailsEvents>(initialValue = ProductDetailsContract.ProductDetailsEvents.Idle) {
            vm.eventsFlow.collect {
                value = it
            }
        }.value

    when (events) {
        is ProductDetailsContract.ProductDetailsEvents.Idle -> {}
        is ProductDetailsContract.ProductDetailsEvents.NavigateToCart -> {
            navController.navigate(ECommerceScreens.CartScreen.name)
        }
        is ProductDetailsContract.ProductDetailsEvents.ShowError -> {
            ErrorToast(message = "Something Went Wrong! Could Not Add it.")
        }

       is  ProductDetailsContract.ProductDetailsEvents.ShowSuccess -> {
            SuccessToast(message = "Product Added Successfully To Cart!")
        }

        else -> {}
    }
}

@Composable
fun ProductDetailsContent(
    navController: NavHostController,
    vm: ProductDetailsViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    tokenViewModel: TokenViewModel = hiltViewModel(),
    product: Product,
    onAddToWishListClick: () -> Unit,
    onAddToCartClick: (quantity: Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        RenderCustomTopBar(nonDetailsTopBarTitle = "Product Details",
            cartViewModel = cartViewModel,
            tokenViewModel = tokenViewModel,
            navController = navController,
            detailsTopBar = false,
            isMainScreen = false, onBackClick = { navController.popBackStack() },
            onCartClick = { vm.invokeAction(ProductDetailsContract.Action.ClickOnCartIcon) }
        )
        ProductDetailsItem(
            product = product,
            onAddToWishListClick = { onAddToWishListClick() },
            onAddToCartClick = { onAddToCartClick(it) }
        )
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetailsItem(
    product: Product,
    onAddToWishListClick: () -> Unit,
    onAddToCartClick: (quantity: Int) -> Unit
) {
    val productQuantity = remember { mutableIntStateOf(1) }
    val productTotalPrice = remember { mutableIntStateOf(0) }
    productTotalPrice.intValue = product.price!! * productQuantity.intValue
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 60.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(shape = RoundedCornerShape(16.dp)) {
            AutoSlidingCarousel(
                itemsCount = product.images?.size ?: 0,
                itemContent = { index ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(product.images?.get(index))
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color(0x4D004182),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                            .width(398.dp)
                            .height(300.dp)
                    )
                }
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier = Modifier.width(250.dp),
                text = product.title ?: "",
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF06004F),

                    )
            )
            Text(
                text = "EGP ${product.price}",
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF06004F),

                    )
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0x4D004182),
                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .padding(0.5.dp)
                    .width(115.dp)
                    .height(35.dp)
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${product.sold} Sold",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF06004F),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Star icon",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "${product.ratingsAverage} (${product.ratingsQuantity})",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF06004F),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Row(
                Modifier
                    .width(122.dp)
                    .height(42.dp)
                    .background(color = Color(0xFF004182), shape = RoundedCornerShape(size = 20.dp))
                    .padding(start = 16.dp, top = 11.dp, end = 16.dp, bottom = 11.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        if (productQuantity.intValue > 1)
                            productQuantity.intValue--
                    },
                    painter = painterResource(id = R.drawable.__icon__subtract_circle_minus_remove_),
                    contentDescription = "Subtract icon",
                    tint = Color.Unspecified
                )
                Text(
                    text = "${productQuantity.intValue}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
                Icon(
                    modifier = Modifier.clickable {
                        if (productQuantity.intValue <= product.quantity!!)
                            productQuantity.intValue++
                    },
                    painter = painterResource(id = R.drawable.__icon__plus_circle_),
                    contentDescription = "plus icon",
                    tint = Color.Unspecified
                )

            }

        }
        Text(
            text = "Description",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF06004F),
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = "${product.description}",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(400),
                color = Color(0x9906004F),
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total price",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0x9906004F),
                        textAlign = TextAlign.Center,
                    )
                )

                Text(
                    text = "${productTotalPrice.intValue} EGP",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF06004F),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Box(modifier = Modifier
                .width(270.dp)
                .height(48.dp)
                .clickable { onAddToCartClick(productQuantity.intValue) }) {
                IconButton(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color(0xFF004182),
                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .padding(start = 32.dp, top = 12.dp, end = 79.dp, bottom = 12.dp),
                    onClick = {
                        //handle add to cart button
                        onAddToCartClick(productQuantity.intValue)
                    }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.cart__2__),
                            contentDescription = "cart icon",
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Add to cart",
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 18.sp,
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 3000L,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(count = itemsCount, state = pagerState) { page ->
            itemContent(page)
        }

        // you can remove the surface in case you don't want
        // the transparent background
        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
            color = Color.Black.copy(alpha = 0.5f)
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = 8.dp
            )
        }
    }

}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = Color(R.color.main_color) /* Color.Yellow */,
    unSelectedColor: Color = Color.Gray /* Color.Gray */,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}

