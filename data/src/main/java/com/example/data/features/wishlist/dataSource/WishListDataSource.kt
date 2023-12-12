package com.example.data.features.wishlist.dataSource

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.addToCart.AddToCartRequest
import com.example.domain.features.products.model.Product
import com.example.domain.features.wishlist.model.WishListResponse
import kotlinx.coroutines.flow.Flow

interface WishListDataSource {
    suspend fun addProductToWishList(token:String,addToCartRequest: AddToCartRequest) : WishListResponse
    suspend fun getLoggedUserWishList(token: String): Flow<ResultWrapper<List<Product>?>?>
    suspend fun removeProductFromWishlist(token : String,productId: String) : Any
}