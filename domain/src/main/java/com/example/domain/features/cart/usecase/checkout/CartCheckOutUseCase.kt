package com.example.domain.features.cart.usecase.checkout

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.ShoppingAddingRequest
import com.example.domain.features.cart.model.check_out.Session
import com.example.domain.features.cart.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartCheckOutUseCase @Inject constructor(
    val repository: CartRepository
) {
    suspend fun invoke(token : String , userId : String): Flow<ResultWrapper<Session>> {
        return repository.checkOut(token,userId)
    }
}