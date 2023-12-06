package com.example.jet_ecommerce.ui.components.toasts

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import es.dmoral.toasty.Toasty

@Composable
fun InfoToast(message : String) {
    val ctx = LocalContext.current
            Toasty.info(ctx, message, Toast.LENGTH_SHORT, true).show()
}