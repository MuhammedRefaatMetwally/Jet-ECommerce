package com.example.jet_ecommerce.ui.components
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    label: String,
    state: MutableState<String>,
    errorState: MutableState<String>,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation,
    focusedLabelColor: Color = Color.Transparent,
    unfocusedLabelColor: Color= Color.Transparent,
    containerColor : Color =  Color.Transparent,

    ) {
    OutlinedTextField(
        value = state.value,

        onValueChange = { newValue ->
            state.value = newValue
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = containerColor,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor =unfocusedLabelColor ,
            errorLabelColor = Color.Red
        ),

        shape = RoundedCornerShape(CornerSize(16.dp)),

        label = {
            Text(
                text = label,
                fontSize = 18.sp,
            )
        },
        isError = errorState.value.isNotEmpty(),
        modifier = Modifier
            .padding(
               // vertical = 8.dp,
                horizontal = 16.dp
            )
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
    if (errorState.value.isNotEmpty()) {
        Text(
            text = errorState.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            style = TextStyle(
                color = Color.Red,
                textAlign = TextAlign.End,
                fontSize = 16.sp
            )
        )
    }


}