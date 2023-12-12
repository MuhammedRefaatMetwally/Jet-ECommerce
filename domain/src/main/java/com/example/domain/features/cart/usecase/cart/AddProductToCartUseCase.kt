package com.example.domain.features.cart.usecase.cart

import com.example.domain.features.cart.model.addToCart.AddToCartRequest
import com.example.domain.features.cart.model.addToCart.CartResponse
import com.example.domain.features.cart.repository.CartRepository
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
    val repository: CartRepository
) {
    suspend operator fun invoke(token:String,addToCartRequest: AddToCartRequest): CartResponse? {
       return repository.addProductToCart(token,addToCartRequest)
    }
}