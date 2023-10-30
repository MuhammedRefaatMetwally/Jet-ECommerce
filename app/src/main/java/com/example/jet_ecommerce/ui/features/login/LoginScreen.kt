package com.example.jet_ecommerce.ui.features.login

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jet_ecommerce.R
import com.example.jet_ecommerce.ui.components.CustomButton
import com.example.jet_ecommerce.ui.components.CustomText
import com.example.jet_ecommerce.ui.components.CustomTextButton
import com.example.jet_ecommerce.ui.components.CustomTextField
import com.example.jet_ecommerce.ui.viewModels.loginViewModel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold(

        modifier = Modifier
            .fillMaxSize(),

        content = {

            LoginContent()

        })


}

@Composable
fun LoginContent(viewModel: LoginViewModel = viewModel()) {

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
                .padding(vertical = 48.dp)
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
            modifier = Modifier
                .padding(horizontal = 16.dp),


            text = stringResource(R.string.welcome_back_to_route),
            fontSize = 24,
            fontWeight = FontWeight(600)
        )

        CustomText(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = stringResource(R.string.please_sign_in_with_your_mail),

            fontWeight = FontWeight(300),
            fontSize = 16
        )
        Spacer(modifier = Modifier.padding(top = 48.dp))
        CustomText(
            text = stringResource(R.string.user_name),

            fontWeight = FontWeight(600),
            fontSize = 18
        )
       // Spacer(modifier = Modifier.padding(top = 0.dp))

        CustomTextField(
            label = stringResource(
                R.string.enter_your_email),
            state = viewModel.emailState,
            errorState = viewModel.emailError ,
            keyboardType = KeyboardType.Email ,
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
            state =  viewModel.passwordState,
            errorState =viewModel.passwordError ,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(top = 4.dp))


        CustomTextButton(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(end = 16.dp),
            text = stringResource(R.string.forget_password),

            fontWeight = FontWeight(400),
            fontSize = 16,
            onClick = {}

        )

        Spacer(modifier = Modifier.padding(top = 4.dp, bottom = 48.dp))

        CustomButton(title = stringResource(R.string.login),
            onClick = {})
        Spacer(modifier = Modifier.padding(top = 10.dp))

        CustomTextButton(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = stringResource(R.string.don_t_have_an_account_create_account),

            fontWeight = FontWeight(400),
            fontSize = 16,
            onClick = {}
        )



    }

}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun preview() {
    LoginScreen(navController = rememberNavController())

}