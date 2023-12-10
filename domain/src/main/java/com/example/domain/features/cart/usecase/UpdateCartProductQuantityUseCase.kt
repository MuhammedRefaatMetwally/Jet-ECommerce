package com.example.domain.features.cart.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.getLoggedUse.CartQuantity
import com.example.domain.features.cart.model.getLoggedUse.CartQuantityResponse
import com.example.domain.features.cart.model.updateUserCart.UpdateUserCartRequest
import com.example.domain.features.cart.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateCartProductQuantityUseCase @Inject constructor(
    private val repository: CartRepository
){
   suspend operator fun invoke(token: String, updateUserCartRequest: UpdateUserCartRequest, productId: String): CartQuantityResponse? {
       return repository.updateCartProductQuantity(token,updateUserCartRequest,productId)
   }

}