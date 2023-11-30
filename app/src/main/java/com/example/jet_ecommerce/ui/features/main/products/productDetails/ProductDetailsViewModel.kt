package com.example.jet_ecommerce.ui.features.main.products.productDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.usecase.GetSpecificProductUseCase
import com.example.jet_ecommerce.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getSpecificProductUseCase: GetSpecificProductUseCase
) : ViewModel(), ProductDetailsContract.ViewModel {

    private val _states: MutableStateFlow<ProductDetailsContract.States> =
        MutableStateFlow(ProductDetailsContract.States.Loading)
    private val _events: MutableStateFlow<ProductDetailsContract.Events> =
        MutableStateFlow(ProductDetailsContract.Events.Idle)
    override val states = _states
    override val events = _events

    init {
        val productId = savedStateHandle.get<String>("product_id")
        invokeAction(ProductDetailsContract.Action.LoadProduct(productId ?: ""))
    }

    override fun invokeAction(action: ProductDetailsContract.Action) {
        when (action) {
            is ProductDetailsContract.Action.LoadProduct -> {
                loadProduct(action.productId)
            }

            is ProductDetailsContract.Action.AddProductToCart -> {
                addProductToCart(action.productId)
            }

            is ProductDetailsContract.Action.AddProductToWishList -> {
                addProductToWishList(action.productId)
            }

            is ProductDetailsContract.Action.ClickOnCartIcon -> {
                cartIconClick()
            }

//            is ProductDetailsContract.Action.UpdateCartProductQuantity -> {
//                updateCartProductQuantity(action.quantity)
//            }
        }
    }

    private fun updateCartProductQuantity(quantity: Int) {
        TODO("Not yet implemented")
    }

    private fun cartIconClick() {
        viewModelScope.launch {
            _events.emit(ProductDetailsContract.Events.NavigateToCart)
        }
    }

    private fun addProductToWishList(productId: String) {
        TODO("Not yet implemented")
    }

    private fun addProductToCart(productId: String) {
        TODO("Not yet implemented")
//        updateCartProductQuantity()
    }

    private fun loadProduct(productId: String) {
        viewModelScope.launch(ioDispatcher) {
            getSpecificProductUseCase.invoke(productId).collect {
                when (it) {
                    is ResultWrapper.Loading -> {
                        _states.emit(ProductDetailsContract.States.Loading)
                    }

                    is ResultWrapper.Error -> {
                        _states.emit(
                            ProductDetailsContract.States.Error(
                                it.error.message ?: "error"
                            )
                        )
                    }

                    is ResultWrapper.Success -> {
                        _states.emit(ProductDetailsContract.States.Success(it.data))
                    }

                    is ResultWrapper.ServerError -> {
                        _states.emit(
                            ProductDetailsContract.States.Error(
                                it.error.message ?: "server error"
                            )
                        )
                    }
                }
            }
        }
    }
}
