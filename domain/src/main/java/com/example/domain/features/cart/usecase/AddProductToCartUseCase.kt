package com.example.domain.features.cart.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.AddToCartRequest
import com.example.domain.features.cart.model.CartResponse
import com.example.domain.features.cart.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
    val repository: CartRepository
) {
    suspend operator fun invoke(token:String,addToCartRequest: AddToCartRequest):CartResponse? {
       return repository.addProductToCart(token,addToCartRequest)
    }
}