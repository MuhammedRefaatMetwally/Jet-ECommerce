package com.example.jet_ecommerce.ui.features.register

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jet_ecommerce.R
import com.example.jet_ecommerce.ui.components.CustomAlertDialog
import com.example.jet_ecommerce.ui.components.CustomButton
import com.example.jet_ecommerce.ui.components.CustomLoadingWidget
import com.example.jet_ecommerce.ui.components.CustomText
import com.example.jet_ecommerce.ui.components.CustomTextField
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens

@Composable
fun RenderViewState(viewModel: RegisterViewModel, navController: NavHostController) {
    val states by viewModel.states.collectAsState()
    val events by viewModel.events.collectAsState()

    // States
    when (states) {

        is RegisterContract.State.Idle -> {
            RegisterContent()

        }

        is RegisterContract.State.Error -> {

            CustomAlertDialog(showDialog = viewModel.showDialog.value,
                dialogDescription = (states as RegisterContract.State.Error).message,
                onDismiss = { viewModel.showDialog.value = false },
                onConfirm = { viewModel.showDialog.value = false })
        }

        is RegisterContract.State.Loading -> CustomLoadingWidget()

        is RegisterContract.State.Success ->{

            // save token into sharedPreferences
            // Token

        }}


    // Events
    when (events) {
        is RegisterContract.Event.Idle -> {
            RegisterContent()}
        is RegisterContract.Event.NavigateAuthenticatedRegisterToHome -> {
            navController.navigate(ECommerceScreens.MainScreen .name)
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    Scaffold(

        modifier = Modifier
            .fillMaxSize(),

        content = {

            RenderViewState(viewModel = viewModel, navController = navController)


        })

}


@SuppressLint("UnrememberedMutableState")
@Composable
fun RegisterContent(

    vm: RegisterViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(
                    id = R.color.stroke_color_30op
                )
            ),
    ) {

        Image(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .width(237.dp)
                .height(71.1.dp)
                .align(alignment = Alignment.CenterHorizontally),
            painter = painterResource(
                id = R.drawable.route_logo
            ),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )



        CustomText(

            text = stringResource(R.string.full_name),

            fontWeight = FontWeight(600),
            fontSize = 18
        )

        CustomTextField(
            label = stringResource(R.string.enter_your_full_name),
            state = vm.name,
            errorState = vm.nameError,
            keyboardType = KeyboardType.Text,
            visualTransformation = VisualTransformation.None
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        CustomText(
            text = stringResource(R.string.e_mail_address),

            fontWeight = FontWeight(600),
            fontSize = 18
        )

        CustomTextField(
            label = stringResource(R.string.enter_your_email_address),
            state = vm.email,
            errorState = vm.emailError,
            keyboardType = KeyboardType.Email,
            visualTransformation = VisualTransformation.None
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))
        CustomText(
            text = stringResource(R.string.password),

            fontWeight = FontWeight(600),
            fontSize = 18
        )
        Spacer(modifier = Modifier.padding(top = 0.dp))

        CustomTextField(
            label = stringResource(R.string.enter_your_password),
            state = vm.password,
            errorState = vm.passwordError,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )


        Spacer(modifier = Modifier.padding(top = 24.dp))

        CustomText(
            text = stringResource(R.string.rePassword),

            fontWeight = FontWeight(600),
            fontSize = 18
        )

        CustomTextField(
            label = stringResource(R.string.enter_the_rePassword),
            state = vm.rePassword,
            errorState = vm.rePasswordError,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(top = 32.dp))

        CustomButton(title = stringResource(R.string.sign_up),
            onClick = {

                vm.invokeAction(RegisterContract.Action.Register(vm.getRequest()))

            })


    }

}


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun preview() {
    RegisterScreen(navController = rememberNavController())

}