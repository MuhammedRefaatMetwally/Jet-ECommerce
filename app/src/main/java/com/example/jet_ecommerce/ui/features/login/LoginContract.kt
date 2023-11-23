package com.example.jet_ecommerce.ui.features.login

import android.service.credentials.Action
import com.example.domain.features.login.model.LoginEntity
import com.example.domain.features.login.model.LoginRequest
import kotlinx.coroutines.flow.StateFlow

class LoginContract {
    interface ViewModel{
        val state:StateFlow<State>
        val event:StateFlow<Event>
        fun invokeAction(action:Action)
    }

    sealed interface State{
       data object Idle:State
        data object Loading:State
       data class Success(val entity: LoginEntity):State
        data class Error(val message:String):State
        }

    sealed interface Event{
        data object Idle:Event
        data object NavigateAuthenticatedLoginToHome : Event

    }

    sealed interface Action{
        data class Login(val loginRequest: LoginRequest):Action
    }

}