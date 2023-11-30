package com.example.domain.features.cart.repository

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.Cart
import com.example.domain.features.cart.model.CartResponse
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addProductToCart(token:String,productId: String): Flow<CartResponse?>
    suspend fun getLoggedUserCart(token: String): Flow<ResultWrapper<Cart?>?>
}