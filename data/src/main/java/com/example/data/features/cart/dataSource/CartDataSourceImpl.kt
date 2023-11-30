package com.example.data.features.cart.dataSource

import com.example.data.api.WebServices
import com.example.data.safeAPiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.Cart
import com.example.domain.features.cart.model.CartResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartDataSourceImpl @Inject constructor(
    val webServices: WebServices
):CartDataSource{
    override suspend fun addProductToCart(token:String,productId: String): Flow<CartResponse?> {
        return flow {webServices.addProductToCart(token,productId)}
    }

    override suspend fun getLoggedUserCart(token: String): Flow<ResultWrapper<Cart?>?> {
        return safeAPiCall { webServices.getLoggedUserCart(token) }
    }

}