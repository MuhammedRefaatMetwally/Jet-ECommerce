package com.example.data.features.cart.repository

import com.example.data.features.cart.dataSource.CartDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.Cart
import com.example.domain.features.cart.model.CartResponse
import com.example.domain.features.cart.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDataSource: CartDataSource
): CartRepository {
    override suspend fun addProductToCart(token:String,productId: String): Flow<CartResponse?>  {
      return cartDataSource.addProductToCart(token,productId)
    }

    override suspend fun getLoggedUserCart(token: String): Flow<ResultWrapper<Cart?>?> {
        TODO("Not yet implemented")
    }

}