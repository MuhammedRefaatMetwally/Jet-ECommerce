package com.example.jet_ecommerce.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
 fun CustomLoadingWidget(){
    val progressValue = 0.75f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,animationSpec = infiniteRepeatable(animation = tween(900)))

    CircularProgressIndicator(progress = progressAnimationValue)
}