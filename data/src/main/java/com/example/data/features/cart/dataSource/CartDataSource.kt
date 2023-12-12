package com.example.data.features.cart.dataSource

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.ShoppingAddingRequest
import com.example.domain.features.cart.model.addToCart.AddToCartRequest
import com.example.domain.features.cart.model.getLoggedUse.CartQuantity
import com.example.domain.features.cart.model.getLoggedUse.CartQuantityResponse
import com.example.domain.features.cart.model.addToCart.CartResponse
import com.example.domain.features.cart.model.check_out.CashOrderData
import com.example.domain.features.cart.model.check_out.Session
import com.example.domain.features.cart.model.updateUserCart.UpdateUserCartRequest
import kotlinx.coroutines.flow.Flow

interface CartDataSource {
    suspend fun addProductToCart(token:String,addToCartRequest: AddToCartRequest): CartResponse?
    suspend fun updateCartProductQuantity(token: String, updateUserCartRequest: UpdateUserCartRequest, productId: String): CartQuantityResponse?

    suspend fun getLoggedUserCart(token: String): Flow<ResultWrapper<CartQuantity?>?>
    suspend fun deleteSpecificCartItem(token: String,productId : String ) : Any
     suspend fun clearCart(token: String): Any

     suspend fun checkOut(token: String , userId : String) : Flow<ResultWrapper<Session>>
     suspend fun createCashOrder(token: String,userId: String,shoppingAddingRequest: ShoppingAddingRequest) : Flow<ResultWrapper<CashOrderData>>
}