package com.example.jet_ecommerce.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.jet_ecommerce.R
import com.example.jet_ecommerce.ui.features.auth.TokenViewModel
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens

@Composable
fun CustomTopBar(
    tokenViewModel: TokenViewModel = hiltViewModel(),) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(id = R.drawable.route_logo),
                modifier = Modifier.padding(8.dp),
                contentDescription = "logo"
            )
            Spacer(modifier = Modifier.fillMaxWidth(.85f))
            Image(
                imageVector = Icons.Default.ExitToApp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        tokenViewModel.deleteToken()

                    },
                contentDescription = "LogOut",
                colorFilter = ColorFilter.tint(colorResource(id = R.color.main_color))
            )
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            SearchField(placeholderText = "what do you search for?")
            Image(
                painter = painterResource(id = R.drawable.__icon__shopping_cart_),
                modifier = Modifier
                    .padding(top = 28.dp)
                    .size(30.dp),
                contentDescription = "cart"
            )
        }

    }
}