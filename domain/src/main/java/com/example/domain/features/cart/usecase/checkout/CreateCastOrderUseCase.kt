package com.example.domain.features.cart.usecase.checkout

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.ShoppingAddingRequest
import com.example.domain.features.cart.model.check_out.CashOrderData
import com.example.domain.features.cart.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateCastOrderUseCase @Inject constructor(
    val repository: CartRepository
) {
    suspend fun invoke(token : String ,  userId : String , shoppingAddingRequest: ShoppingAddingRequest): Flow<ResultWrapper<CashOrderData>> {
        return repository.createCashOrder(token,userId,shoppingAddingRequest)
    }
}