package com.example.jet_ecommerce.ui.features.auth.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.register.model.RegisterRequest
import com.example.domain.features.register.usecase.GetRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val getRegisterUseCase: GetRegisterUseCase,
) : ViewModel(), RegisterContract.ViewModel {

    override val states: StateFlow<RegisterContract.State> get() = _states
    private val _states = MutableStateFlow<RegisterContract.State>(RegisterContract.State.Idle)

    override val events: StateFlow<RegisterContract.Event> get() = _events
    private val _events = MutableStateFlow<RegisterContract.Event>(RegisterContract.Event.Idle)

    var name = mutableStateOf("")
    var nameError = mutableStateOf("")

    var email = mutableStateOf("")
    var emailError = mutableStateOf("")

    var password = mutableStateOf("")
    var passwordError = mutableStateOf("")

    var rePassword = mutableStateOf("")
    var rePasswordError = mutableStateOf("")

    var showDialog = mutableStateOf(true)
    fun getRequest():RegisterRequest{
        return RegisterRequest(name.value,email.value,password.value,rePassword.value)

    }
    override fun invokeAction(action: RegisterContract.Action) {

        when (action) {
            is RegisterContract.Action.Register -> validateAndRegister(action.registerRequest)
            else -> {}
        }
    }
    private fun validateFields():Boolean{
        if (name.value.isEmpty() || name.value.isBlank()) {
            nameError.value = "name required"
            return false
        } else {
            nameError.value = ""
        }
        if (email.value.isEmpty() || email.value.isBlank()) {
            emailError.value = "Email Required"
            return false
        } else
            emailError.value = ""

        if (password.value.isEmpty() || password.value.isBlank()) {
            passwordError.value = "Password Required"
            return false
        } else
            passwordError.value = ""

        if (rePassword.value.isEmpty() || rePassword.value.isBlank()) {
            rePasswordError.value = "RePassword Required"
            return false
        } else{
            rePasswordError.value = ""
        }
        return true
    }

  private  fun validateAndRegister(registerRequest: RegisterRequest){
        if (!validateFields()) return
        showDialog.value = true
        register(registerRequest)
    }
    private fun register(registerRequest: RegisterRequest){

        _states.value = RegisterContract.State.Loading


            viewModelScope.launch {
                try {


                    val data = getRegisterUseCase(registerRequest)
                    _states.value = RegisterContract.State.Success(data)
                    _events.value = RegisterContract.Event.NavigateAuthenticatedRegisterToHome
                }catch (ex:Exception){
                    _states.value = RegisterContract.State.Error("${ex.message}")

                }
            }
        }



    }

