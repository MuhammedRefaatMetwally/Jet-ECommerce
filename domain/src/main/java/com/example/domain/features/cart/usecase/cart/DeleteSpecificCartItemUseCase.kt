package com.example.domain.features.cart.usecase.cart

import com.example.domain.features.cart.repository.CartRepository
import javax.inject.Inject

class DeleteSpecificCartItemUseCase @Inject constructor(
    val repository: CartRepository
) {

    suspend fun invoke(token : String , productId : String ){
        repository.deleteSpecificCartItem(token,productId)
    }
}