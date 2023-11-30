package com.example.jet_ecommerce.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.jet_ecommerce.R

@Composable
 fun CustomLoadingWidget(){
    val progressValue = 0.75f
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,animationSpec = infiniteRepeatable(animation = tween(900)),
        label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(progress = progressAnimationValue, color = colorResource(id = R.color.main_color))
    }
}