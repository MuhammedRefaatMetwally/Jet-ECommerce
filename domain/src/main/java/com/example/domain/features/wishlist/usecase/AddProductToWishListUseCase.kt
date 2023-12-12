package com.example.domain.features.wishlist.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.addToCart.AddToCartRequest
import com.example.domain.features.wishlist.model.WishListResponse
import com.example.domain.features.wishlist.repository.WishListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddProductToWishListUseCase @Inject constructor(
    val repository: WishListRepository
) {
    suspend operator fun invoke(token:String,addToCartRequest: AddToCartRequest) : WishListResponse{
       return repository.addProductToWishList(token,addToCartRequest)
    }
}