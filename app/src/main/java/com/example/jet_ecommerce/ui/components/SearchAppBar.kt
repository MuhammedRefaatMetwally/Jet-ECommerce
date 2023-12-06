package com.example.jet_ecommerce.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jet_ecommerce.R


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

@Composable
@Preview()
fun SearchField(
    modifier: Modifier = Modifier,
    searchText: String = "",
    placeholderText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    onSearchClick: () -> Unit = {},
    onClose: () -> Unit = {}
) {
    val showClearButton by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(.9f).padding(8.dp),
        value = searchText,
        onValueChange = onSearchTextChanged,
        placeholder = {
            Text(
                text = placeholderText,
                style = TextStyle(color = Color.LightGray, fontSize = 16.sp)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorResource(id = R.color.main_color),
            disabledTextColor = colorResource(id = R.color.main_color),
            cursorColor = colorResource(id = R.color.main_color),
            focusedBorderColor = colorResource(id = R.color.text_color),
            unfocusedBorderColor = colorResource(id = R.color.text_color),
            unfocusedLabelColor = colorResource(id = R.color.text_color),
            focusedSupportingTextColor = Color.Red,
        ),
        shape = RoundedCornerShape(CornerSize(24.dp)),

        leadingIcon = {
                IconButton(onClick = { onSearchClick() }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.__icon__search_),
                        contentDescription = "close",
                        tint = colorResource(id = R.color.main_color)
                    )
                }

        },
        trailingIcon = {
            IconButton(onClick = { onClose() }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    modifier = Modifier.size(24.dp),
                    contentDescription = "",
                    tint = colorResource(id = R.color.main_color)
                )
            }
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
    )
   /* LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.hide()
    }*/

}
