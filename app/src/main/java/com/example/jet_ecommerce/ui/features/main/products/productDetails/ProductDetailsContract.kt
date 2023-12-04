package com.example.jet_ecommerce.ui.features.main.products.productDetails

import com.example.domain.features.cart.model.CartResponse
import com.example.domain.features.products.model.Product
import kotlinx.coroutines.flow.StateFlow

class ProductDetailsContract {

    interface ViewModel {
        val productDetailsStates: StateFlow<ProductDetailsStates>
        val addToCartStates:StateFlow<AddToCartStates>
        val addToWishListStates:StateFlow<AddToWishListStates>
        val productDetailsEvents: StateFlow<ProductDetailsEvents>
        fun invokeAction(action: Action)
    }


    sealed interface ProductDetailsStates {

        data object Loading : ProductDetailsStates
        data class Error(val message: String) : ProductDetailsStates
        data class Success(val product: Product) : ProductDetailsStates
    }
    sealed interface AddToCartStates{
        data object Idle:AddToCartStates
        data object Loading:AddToCartStates
        data class Error(val message: String):AddToCartStates
        data class Success(val response: CartResponse):AddToCartStates
    }
    sealed interface AddToWishListStates{
        data object Idle:AddToWishListStates
        data object Loading:AddToWishListStates
        data class Error(val message: String):AddToWishListStates
        data class Success(val response: CartResponse):AddToWishListStates
    }

    sealed interface ProductDetailsEvents {
        data object Idle : ProductDetailsEvents
        data object NavigateToCart : ProductDetailsEvents
    }

    sealed interface Action {
        data class LoadProduct(val productId: String) : Action
        data class AddProductToCart(val productId: String ,val quantity:Int) : Action
        data class AddProductToWishList(val productId: String) : Action
        data object ClickOnCartIcon : Action
    }
}