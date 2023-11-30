package com.example.data.features.wishlist.dataSource

import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.model.Product
import com.example.domain.features.wishlist.model.WishListResponse
import kotlinx.coroutines.flow.Flow

interface WishListDataSource {
    suspend fun addProductToWishList(token:String,productId: String): Flow<WishListResponse?>
    suspend fun getLoggedUserWishList(token: String): Flow<ResultWrapper<List<Product>?>?>
}