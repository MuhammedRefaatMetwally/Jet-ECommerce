package com.example.domain.features.cart.repository

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.AddToCartRequest
import com.example.domain.features.cart.model.Cart
import com.example.domain.features.cart.model.CartQuantityResponse
import com.example.domain.features.cart.model.CartResponse
import com.example.domain.features.cart.model.UpdateUserCartRequest
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addProductToCart(token:String,addToCartRequest: AddToCartRequest): CartResponse?
    suspend fun updateCartProductQuantity(token: String, updateUserCartRequest: UpdateUserCartRequest, productId: String): CartQuantityResponse?
    suspend fun getLoggedUserCart(token: String): Flow<ResultWrapper<Cart?>?>
}