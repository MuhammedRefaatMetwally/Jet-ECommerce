package com.example.domain.features.cart.usecase

import com.example.domain.features.cart.model.CartQuantityResponse
import com.example.domain.features.cart.model.CartResponse
import com.example.domain.features.cart.model.UpdateUserCartRequest
import com.example.domain.features.cart.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateCartProductQuantityUseCase @Inject constructor(
    private val repository: CartRepository
){
   suspend operator fun invoke(token: String, updateUserCartRequest: UpdateUserCartRequest, productId: String): CartQuantityResponse?{
       return repository.updateCartProductQuantity(token,updateUserCartRequest,productId)
   }

}