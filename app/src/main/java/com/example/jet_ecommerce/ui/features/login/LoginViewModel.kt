package com.example.jet_ecommerce.ui.features.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.login.model.LoginRequest
import com.example.domain.features.login.useCase.GetLoginUseCase
import com.example.jet_ecommerce.ui.features.register.RegisterContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class LoginViewModel @Inject constructor(

    private val getLoginUseCase: GetLoginUseCase,
) :ViewModel(),LoginContract.ViewModel {

    private val _states = MutableStateFlow<LoginContract.State>(LoginContract.State.Idle)

    override val state: StateFlow<LoginContract.State> get() =_states

    private val _events = MutableStateFlow<LoginContract.Event>(LoginContract.Event.Idle)
    override val event: StateFlow<LoginContract.Event> get() = _events
    var email = mutableStateOf("")
    var emailError = mutableStateOf("")

    var password = mutableStateOf("")
    var passwordError = mutableStateOf("")
    var showDialog = mutableStateOf(true)

    fun getRequest():LoginRequest{

        return LoginRequest(email.value,password.value) }

   private fun validateFiled():Boolean{
        if (email.value.isEmpty() || email.value.isEmpty()){
            emailError.value = " Email required"
            return false
        }else{
            emailError.value= ""
        }
        if (password.value.isEmpty()|| password.value.isEmpty()){
            passwordError.value = "Password required"
            return false
        }
        else{
            passwordError.value = ""
        }
        return true
    }



    override fun invokeAction(action: LoginContract.Action) {
        when (action) {
            is LoginContract.Action.Login -> login(action.loginRequest)
        }
    }

    private fun login(loginRequest: LoginRequest){
        _states.value = LoginContract.State.Loading
        if (validateFiled()){
            showDialog.value = false
        }
        else{
            viewModelScope.launch {
                try {
                   val data = getLoginUseCase(loginRequest)
                    _states.value = LoginContract.State.Success(data)
                    _events.value = LoginContract.Event.NavigateAuthenticatedLoginToHome
                }
                catch (ex:Exception){
                    _states.value = LoginContract.State.Error("${ex.message}")
                }
            }

        }
    }
}