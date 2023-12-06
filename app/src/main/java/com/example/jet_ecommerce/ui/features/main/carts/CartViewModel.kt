package com.example.jet_ecommerce.ui.features.main.carts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.TokenManager
import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.getLoggedUse.CartQuantity
import com.example.domain.features.cart.usecase.ClearCartUseCase
import com.example.domain.features.cart.usecase.GetLoggedUserCartUseCase
import com.example.jet_ecommerce.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val getLoggedUserCartUseCase: GetLoggedUserCartUseCase,
    private val clearCartUseCase : ClearCartUseCase,
    private val tokenManager: TokenManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel(), CartContract.ViewModel {


    private lateinit var token: String
    private val _states: MutableStateFlow<CartContract.State> =
        MutableStateFlow(CartContract.State.Idle)
    override val states: StateFlow<CartContract.State>
        get() = _states

    private val _events: MutableStateFlow<CartContract.Event> =
        MutableStateFlow(CartContract.Event.Idle)
    override val events: StateFlow<CartContract.Event>
        get() = _events

    var cartProductsSize : Int? = 0
    init {
        viewModelScope.launch {
            tokenManager.getToken().collect { token = it!! }
        }
    }

    override fun invokeAction(action: CartContract.Action) {
        when (action) {
            CartContract.Action.GetUserProducts -> getUserProducts()
            CartContract.Action.ClearCart -> clearCart()
        }
    }

    private fun clearCart(){
        viewModelScope.launch(ioDispatcher) {
            clearCartUseCase.invoke(token)
        }
    }

    private fun getUserProducts() {
        viewModelScope.launch(ioDispatcher) {
            getLoggedUserCartUseCase.invoke(token).collect {
                when (it) {
                    is ResultWrapper.Error -> {
                        _states.emit(CartContract.State.Error(it.error.message.toString()))

                    }

                    is ResultWrapper.Loading -> {
                        _states.emit(
                            CartContract.State.Loading
                        )

                    }

                    is ResultWrapper.ServerError -> {
                        _states.emit(
                            CartContract.State.Error(it.error.message.toString())
                        )
                    }

                    is ResultWrapper.Success -> {

                        _states.emit(CartContract.State.Success(data = it.data ?: CartQuantity()))
                        cartProductsSize = it.data?.products?.size ?: 0
                    }

                    else -> {}
                }
            }
        }

    }

}