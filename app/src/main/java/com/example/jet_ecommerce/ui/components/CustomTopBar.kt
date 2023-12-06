package com.example.jet_ecommerce.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.jet_ecommerce.R
import com.example.jet_ecommerce.ui.features.auth.TokenViewModel
import com.example.jet_ecommerce.ui.features.main.products.productDetails.ProductDetailsContract
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CustomTopBar(
    modifier: Modifier = Modifier,
    isMainScreen: Boolean = false,
    detailsTopBar: Boolean = true,
    onCartClick: () -> Unit = {},
    cartItemsSize: Int = 0,
    nonDetailsTopBarTitle: String = "",
    onLongCartClick : ()->Unit ={},
    onMainCartClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    if (detailsTopBar) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Image(
                    painter = painterResource(id = R.drawable.route_logo),
                    modifier = Modifier.padding(8.dp),
                    contentDescription = "logo"
                )
                if (isMainScreen) {
                    Spacer(modifier = Modifier.fillMaxWidth(.85f))
                    Image(
                        imageVector = Icons.Default.ExitToApp,
                        modifier = modifier
                            .padding(8.dp),
                        contentDescription = "LogOut",
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.main_color))
                    )
                }

            }

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                SearchField(placeholderText = "what do you search for?")
                Box(contentAlignment = Alignment.TopEnd) {
                    Surface(
                        modifier = Modifier
                            .size(24.dp),
                        color= colorResource(id = R.color.main_color),
                        contentColor = Color.White,
                        shape = CircleShape
                    ) {
                        Text(text = "$cartItemsSize", textAlign = TextAlign.Center)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.__icon__shopping_cart_),
                        modifier = Modifier
                            .padding(top =16.dp, end = 8.dp)
                            .clickable {
                                onMainCartClick()
                            }
                            .size(40.dp),
                        contentDescription = "cart"
                    )
                }

            }

        }
    } else {
        TopAppBar(title = {
            Text(
                nonDetailsTopBarTitle,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }, navigationIcon = {
            IconButton(modifier= Modifier.padding(top = 16.dp),onClick = {
                onBackClick()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.__icon__arrow_back_outline_),
                    contentDescription = "Back",
                    tint = Color.Unspecified
                )
            }
        }, actions = {
            IconButton(modifier = Modifier.padding(top = 16.dp),onClick = { /* Handle search icon click */ }) {
                Icon(
                    painterResource(id = R.drawable.__icon__search_), contentDescription = "Search",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = { onCartClick() }) {
                Box(contentAlignment = Alignment.TopEnd) {
                    Surface(
                        modifier = Modifier
                            .size(24.dp),
                        color= colorResource(id = R.color.main_color),
                        contentColor = Color.White,
                        shape = CircleShape
                    ) {
                        Text(text = "$cartItemsSize", textAlign = TextAlign.Center)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.__icon__shopping_cart_),
                        modifier = Modifier
                            .padding(top = 16.dp,end = 8.dp)
                            .combinedClickable(onLongClick = {onLongCartClick()}) {
                                onMainCartClick()
                            },
                        contentDescription = "cart"
                    )
                }
            }
        })
    }

}