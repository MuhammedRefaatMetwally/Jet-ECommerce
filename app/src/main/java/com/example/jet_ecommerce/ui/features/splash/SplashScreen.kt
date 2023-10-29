package com.example.jet_ecommerce.ui.features.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.jet_ecommerce.R
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(0.9f , animationSpec = tween(
            durationMillis = 800,
            easing = {
                OvershootInterpolator(8f).getInterpolation(it)
            }
        ))
        delay(2500L)
        navController.navigate(ECommerceScreens.MainScreen.name)
    }
    SplashContent()
}

@Composable
fun SplashContent() {
    Surface(Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.splash_bg), contentDescription ="splash", contentScale = ContentScale.Crop )
    }
}