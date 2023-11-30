package com.example.domain.features.wishlist.repository

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.Cart
import com.example.domain.features.cart.model.CartResponse
import com.example.domain.features.products.model.Product
import com.example.domain.features.wishlist.model.WishListResponse
import kotlinx.coroutines.flow.Flow

interface WishListRepository {
    suspend fun addProductToWishList(token:String,productId: String): Flow<ResultWrapper<WishListResponse?>?>
    suspend fun getLoggedUserWishList(token: String): Flow<ResultWrapper<List<Product>?>?>
}