package com.example.domain.features.cart.usecase.cart

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.getLoggedUse.CartQuantity
import com.example.domain.features.cart.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoggedUserCartUseCase @Inject constructor(
    val repository: CartRepository
) {
    suspend operator fun invoke(token:String): Flow<ResultWrapper<CartQuantity?>?> {
        return repository.getLoggedUserCart(token)
    }
}