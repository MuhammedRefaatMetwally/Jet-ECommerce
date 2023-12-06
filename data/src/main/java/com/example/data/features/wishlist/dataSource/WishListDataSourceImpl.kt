package com.example.data.features.wishlist.dataSource

import com.example.data.api.WebServices
import com.example.data.features.cart.dataSource.CartDataSource
import com.example.data.safeAPiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.model.Product
import com.example.domain.features.wishlist.model.WishListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WishListDataSourceImpl @Inject constructor(
    val webServices: WebServices
): WishListDataSource {


    override suspend fun addProductToWishList(token: String, productId: String): Flow<WishListResponse> {
      return flow {webServices.addProductToWishList(token,productId)  }
    }

    override suspend fun getLoggedUserWishList(token: String): Flow<ResultWrapper<List<Product>?>?> {
        return  safeAPiCall { webServices.getLoggedUserWishList(token) }
    }

}