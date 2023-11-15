package com.example.jet_ecommerce.ui.features.main.products.productDetails

import com.example.domain.features.products.model.Product
import kotlinx.coroutines.flow.StateFlow

class ProductDetailsContract {

    interface ViewModel {
        val states: StateFlow<States>
        val events: StateFlow<Events>
        fun invokeAction(action: Action)
    }

    sealed interface States {
        data object Loading : States
        data class Error(val message: String) : States
        data class Success(val product: Product) : States
    }

    sealed interface Events {
        data object Idle : Events
        data object NavigateToCart : Events
    }

    sealed interface Action {
        data class LoadProduct(val productId: String) : Action
        data class AddProductToCart(val product: Product) : Action
        data class AddProductToWishList(val product: Product) : Action
        data class UpdateCartProductQuantity(val quantity: Int) : Action
        data object ClickOnCartIcon : Action
    }
}