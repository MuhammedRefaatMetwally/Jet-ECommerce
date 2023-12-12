package com.example.domain.features.wishlist.usecase

import com.example.domain.features.wishlist.repository.WishListRepository
import javax.inject.Inject

class RemoveProductFromWishListUseCase @Inject constructor(val repository: WishListRepository) {
     suspend  operator fun invoke(token : String,productId : String){
        repository.removeProductFromWishlist(token,productId)
    }
}