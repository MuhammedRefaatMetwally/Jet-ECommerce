package com.example.data.features.wishlist.repository

import com.example.data.features.cart.dataSource.CartDataSource
import com.example.data.features.wishlist.dataSource.WishListDataSource
import com.example.data.safeAPiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.Cart
import com.example.domain.features.cart.model.CartResponse
import com.example.domain.features.cart.repository.CartRepository
import com.example.domain.features.products.model.Product
import com.example.domain.features.wishlist.model.WishListResponse
import com.example.domain.features.wishlist.repository.WishListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WishListRepositoryImpl @Inject constructor(
    private val wishListDataSource: WishListDataSource
): WishListRepository {
    override suspend fun addProductToWishList(
        token: String,
        productId: String
    ): Flow<ResultWrapper<WishListResponse?>?> {
        return flow { wishListDataSource.addProductToWishList(token, productId) }
    }

    override suspend fun getLoggedUserWishList(token: String): Flow<ResultWrapper<List<Product>?>?> {
        return wishListDataSource.getLoggedUserWishList(token)
    }


}