package com.example.domain.features.cart.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.Cart
import com.example.domain.features.cart.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoggedUserCartUseCase @Inject constructor(
    val repository: CartRepository
) {
    suspend operator fun invoke(token:String):Flow<ResultWrapper<Cart?>?>{
        return repository.getLoggedUserCart(token)
    }
}