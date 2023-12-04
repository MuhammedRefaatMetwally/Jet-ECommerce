package com.example.jet_ecommerce.ui.features.auth.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jet_ecommerce.R
import com.example.jet_ecommerce.ui.components.CustomAlertDialog
import com.example.jet_ecommerce.ui.components.CustomButton
import com.example.jet_ecommerce.ui.components.CustomLoadingWidget
import com.example.jet_ecommerce.ui.components.CustomText
import com.example.jet_ecommerce.ui.components.CustomTextButton
import com.example.jet_ecommerce.ui.components.CustomTextField
import com.example.jet_ecommerce.ui.features.auth.TokenViewModel
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens

@Composable
fun RenderViewState(navController: NavHostController, viewModel: LoginViewModel,tokenViewModel: TokenViewModel = hiltViewModel()) {
    val states by viewModel.state.collectAsState()
    val events by viewModel.event.collectAsState()

    when (states) {
        is LoginContract.State.Idle -> {
            LoginContent(navController = navController)
        }

        is LoginContract.State.Loading -> CustomLoadingWidget()



        is LoginContract.State.Success -> {
            tokenViewModel.saveToken((states as LoginContract.State.Success).data.token ?: "N/A")
        }

        is LoginContract.State.Error -> {
    CustomAlertDialog(dialogTitle = "Ops! Error",showDialog = viewModel.showDialog.value,
                dialogDescription = (states as LoginContract.State.Error).message,
                onDismiss = { viewModel.showDialog.value = false },
                onConfirm = { viewModel.showDialog.value = false })
        }
    }

    when (events) {

        is LoginContract.Event.Idle -> {
            LoginContent(navController = navController)
        }

        LoginContract.Event.NavigateToHome -> {
            navController.navigate(ECommerceScreens.MainScreen.name)

        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Scaffold(

        modifier = Modifier
            .fillMaxSize(),

        content = {

            RenderViewState(navController = navController, viewModel = viewModel)

        })


}

@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginContent(viewModel: LoginViewModel = hiltViewModel(), navController: NavHostController) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(
                    id = R.color.main_color
                )
            ),
    ) {


        Image(
            modifier = Modifier
                .padding(vertical = 48.dp)
                .width(237.dp)
                .height(71.1.dp)
                .align(alignment = Alignment.CenterHorizontally),

            painter = painterResource(
                id = R.drawable.route_logo
            ),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color.White)
        )
        CustomText(
            modifier = Modifier
                .padding(horizontal = 16.dp),


            text = stringResource(R.string.welcome_back_to_route),
            fontSize = 24.sp,

            fontWeight = FontWeight(600),
            color = Color.White
        )

        CustomText(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = stringResource(R.string.please_sign_in_with_your_mail),

            fontWeight = FontWeight(300),
            fontSize = 16.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.padding(top = 48.dp))
        CustomText(
            text = "Email",

            fontWeight = FontWeight(600),
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp,bottom = 8.dp)
        )


        CustomTextField(
            label ="example@example.com",
            state = viewModel.email,
            errorState = viewModel.emailError,
            keyboardType = KeyboardType.Email,
            visualTransformation = VisualTransformation.None,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Black,
            containerColor = Color.White
        )
        Spacer(modifier = Modifier.padding(top = 24.dp))

        CustomText(
            text = stringResource(R.string.password),

            fontWeight = FontWeight(600),
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp,bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = stringResource(R.string.enter_your_password),
            state = viewModel.password,
            errorState = viewModel.passwordError,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Black,
            containerColor = Color.White
        )
        Spacer(modifier = Modifier.padding(top = 4.dp))


        CustomTextButton(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(end = 16.dp),
            text = stringResource(R.string.forget_password),

            fontWeight = FontWeight.ExtraBold,
            fontSize = 16,
            onClick = {}

        )

        Spacer(modifier = Modifier.padding(top = 4.dp, bottom = 24.dp))

        CustomButton(title = stringResource(R.string.login),
            onClick = {
                viewModel.invokeAction(LoginContract.Action.Login(viewModel.getRequest()))
            })

        Spacer(modifier = Modifier.padding(top = 10.dp))


        ClickableText(text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                fontSize = 18.sp,
                color = Color.White
            )){
                append("Don't have an account?  ")
            }
            withStyle(style = SpanStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,

                )){
                append("Create Account")
            } },modifier = Modifier.align(Alignment.CenterHorizontally), onClick ={  navController.navigate(ECommerceScreens.RegisterScreen.name) } )

    }

}
