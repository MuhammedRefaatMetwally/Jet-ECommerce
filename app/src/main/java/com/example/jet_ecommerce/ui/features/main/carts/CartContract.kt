package com.example.jet_ecommerce.ui.features.main.carts

import com.example.domain.features.cart.model.getLoggedUse.CartQuantity
import com.example.domain.features.cart.model.getLoggedUse.ProductItem
import com.example.domain.features.products.model.Product

import kotlinx.coroutines.flow.StateFlow

sealed class CartContract {
    sealed interface ViewModel {

        val states: StateFlow<State>
        val events: StateFlow<Event>
        fun invokeAction(action: Action)
    }

    sealed interface State {
        data object Idle : State
        class Error(val message: String) : State
        class Success(val data: CartQuantity) : State
        data object Loading : State
    }

    sealed interface Event {
        data object Idle : Event
        data object  ShowSuccess  : Event
        data object  ShowError  : Event
    }

    sealed interface Action {
        data object GetUserProducts : Action
        data class DeleteSpecificCartItem(val productId: String, val product: ProductItem?) : Action
        data object ClearCart : Action

    }
}