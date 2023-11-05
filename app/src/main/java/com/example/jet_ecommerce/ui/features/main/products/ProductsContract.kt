package com.example.jet_ecommerce.ui.features.main.products

import com.example.domain.features.product.model.Product
import kotlinx.coroutines.flow.StateFlow

class ProductsContract {
    interface ViewModel {
        val states: StateFlow<States>
        val events: StateFlow<Events>
        fun invokeAction(action: Action)
    }

    sealed interface States {
        data object Loading : States
        data class Error(val message: String?) : States
        data class Success(val productsList: List<Product?>?) : States
    }

    sealed interface Events {
        data object Idle : Events
        data class NavigateToProductDetailsScreen(val product: Product?) : Events
    }

    sealed interface Action {
        data class LoadProducts(val categoryId: String? = null):Action
        data class ProductClick(val product: Product?) : Action
        data class AddToWishListButtonClick(val product: Product?) : Action
        data class AddToCartButtonClick(val product: Product?) : Action
    }
}