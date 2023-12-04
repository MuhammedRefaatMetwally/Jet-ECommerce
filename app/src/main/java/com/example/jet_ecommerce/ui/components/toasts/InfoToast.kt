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
fun InfoToast() {
    val ctx = LocalContext.current
    Button(
        modifier = Modifier
            .width(300.dp)
            .padding(7.dp),
        onClick = {
            Toasty.info(ctx, "This is a Info toast.", Toast.LENGTH_SHORT, true).show()

        }) {
        Text(text = "Info Toast")
    }
}