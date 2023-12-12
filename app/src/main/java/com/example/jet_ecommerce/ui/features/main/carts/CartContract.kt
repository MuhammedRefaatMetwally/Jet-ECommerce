package com.example.jet_ecommerce.ui.features.main.carts

import com.example.domain.features.cart.model.ShoppingAddingRequest
import com.example.domain.features.cart.model.check_out.CashOrderData
import com.example.domain.features.cart.model.check_out.CreateCastOrderResponse
import com.example.domain.features.cart.model.check_out.Session
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
        class Success(
            val data: CartQuantity = CartQuantity(),
            val checkOutData: Session = Session(),
            val createCheckoutData :
            CashOrderData = CashOrderData()
        ) : State

        data object Loading : State
    }

    sealed interface Event {
        data object Idle : Event
        data object ShowSuccess : Event
        data object ShowError : Event
        data object NavigateToStripe : Event
    }

    sealed interface Action {
        data object GetUserProducts : Action
        data class DeleteSpecificCartItem(val productId: String, val product: ProductItem?) : Action
        data class UpdateCartProductQuantity(val count: Int, val productId: String) : Action
        data object ClearCart : Action

        data class CheckOut(val userId: String) : Action

        data class CreateCastOrder(
            val userId: String,
            val shoppingAddingRequest: ShoppingAddingRequest
        ) : Action

    }
}