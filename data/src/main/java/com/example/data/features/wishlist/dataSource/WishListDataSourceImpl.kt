package com.example.data.features.wishlist.dataSource

import com.example.data.api.WebServices
import com.example.data.safeAPiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.addToCart.AddToCartRequest
import com.example.domain.features.products.model.Product
import com.example.domain.features.wishlist.model.WishListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WishListDataSourceImpl @Inject constructor(
    val webServices: WebServices
): WishListDataSource {


    override suspend fun addProductToWishList(token: String, addToCartRequest: AddToCartRequest) : WishListResponse  {
      return webServices.addProductToWishList(token,addToCartRequest)
    }

    override suspend fun getLoggedUserWishList(token: String): Flow<ResultWrapper<List<Product>?>?> {
        return  safeAPiCall { webServices.getLoggedUserWishList(token) }
    }

    override suspend fun removeProductFromWishlist(token : String,productId: String) {
        webServices.removeProductFromWishList(token = token, productId)
    }

}