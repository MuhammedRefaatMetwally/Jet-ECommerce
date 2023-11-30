package com.example.jet_ecommerce.ui.features.auth.register

import com.example.domain.features.register.model.RegisterEntity
import com.example.domain.features.register.model.RegisterRequest
import kotlinx.coroutines.flow.StateFlow

sealed class RegisterContract {
    sealed interface ViewModel {

        val states: StateFlow<State>
        val events: StateFlow<Event>
        fun invokeAction(action: Action)
    }

    sealed interface State {
        data object Idle : State
        class Error(val message: String) : State
        class Success(val entity: RegisterEntity) : State
        data object Loading : State
    }

    sealed interface Event {
        data object Idle : Event
        data object NavigateAuthenticatedRegisterToHome : Event

    }

    sealed interface Action {

        data class Register(val registerRequest: RegisterRequest) : Action

    }
}