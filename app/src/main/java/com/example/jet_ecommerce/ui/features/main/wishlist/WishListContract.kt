package com.example.jet_ecommerce.ui.features.main.wishlist

import com.example.domain.features.cart.model.addToCart.AddToCartRequest
import com.example.domain.features.products.model.Product
import com.example.domain.features.wishlist.model.WishListResponse
import kotlinx.coroutines.flow.StateFlow

class WishListContract {
    sealed interface ViewModel {

        val states: StateFlow<State>
        val events: StateFlow<Event>
        fun invokeAction(action: Action)
    }

    sealed interface State {
        data object Idle : State
        class Error(val message: String) : State
        class Success(val data: WishListResponse? , val productData : List<Product>) : State
        data object Loading : State
    }

    sealed interface Event {
        data object Idle : Event
        data object  ShowSuccess  : Event
        data object  ShowError  : Event
    }

    sealed interface Action {
        data object GetWishListProducts : Action
        data class RemoveProductFomWishList(val productId: String) : Action
        data class AddProductToWishLIst(val addToCartRequest: AddToCartRequest) : Action

    }
}