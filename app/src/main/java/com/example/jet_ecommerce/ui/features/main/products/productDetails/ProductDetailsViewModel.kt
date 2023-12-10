package com.example.jet_ecommerce.ui.features.main.products.productDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.TokenManager
import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.addToCart.AddToCartRequest
import com.example.domain.features.cart.model.updateUserCart.UpdateUserCartRequest
import com.example.domain.features.cart.usecase.AddProductToCartUseCase
import com.example.domain.features.cart.usecase.UpdateCartProductQuantityUseCase
import com.example.domain.features.products.usecase.GetSpecificProductUseCase
import com.example.jet_ecommerce.IoDispatcher
import com.example.jet_ecommerce.ui.features.main.carts.CartContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getSpecificProductUseCase: GetSpecificProductUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val updateCartProductQuantityUseCase: UpdateCartProductQuantityUseCase,
    private val tokenManager: TokenManager,
) : ViewModel(), ProductDetailsContract.ViewModel {

    private val _productDetailsStates: MutableStateFlow<ProductDetailsContract.ProductDetailsStates> =
        MutableStateFlow(ProductDetailsContract.ProductDetailsStates.Loading)
    private val _productDetailsEvents: MutableStateFlow<ProductDetailsContract.ProductDetailsEvents> =
        MutableStateFlow(ProductDetailsContract.ProductDetailsEvents.Idle)
    override val productDetailsStates get() = _productDetailsStates
    override val productDetailsEvents get() = _productDetailsEvents

    private val _addToCartStates: MutableStateFlow<ProductDetailsContract.AddToCartStates> =
        MutableStateFlow(ProductDetailsContract.AddToCartStates.Idle)
    override val addToCartStates: StateFlow<ProductDetailsContract.AddToCartStates>
        get() = _addToCartStates

    private val _addToWishListStates: MutableStateFlow<ProductDetailsContract.AddToWishListStates> =
        MutableStateFlow(ProductDetailsContract.AddToWishListStates.Idle)
    override val addToWishListStates: StateFlow<ProductDetailsContract.AddToWishListStates>
        get() = _addToWishListStates
    private lateinit var token: String

    private val eventChannel = Channel<ProductDetailsContract.ProductDetailsEvents>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()//single live event
    init {
        viewModelScope.launch {
            tokenManager.getToken().collect { token = it!! }
        }

        val productId = savedStateHandle.get<String>("product_id")
        invokeAction(ProductDetailsContract.Action.LoadProduct(productId ?: ""))
    }

    override fun invokeAction(action: ProductDetailsContract.Action) {
        when (action) {
            is ProductDetailsContract.Action.LoadProduct -> {
                loadProduct(action.productId)
            }

            is ProductDetailsContract.Action.AddProductToCart -> {
                addProductToCart(action.productId, action.quantity)
            }

            is ProductDetailsContract.Action.AddProductToWishList -> {
                addProductToWishList(action.productId)
            }

            is ProductDetailsContract.Action.ClickOnCartIcon -> {
                cartIconClick()
            }

        }
    }

    private fun cartIconClick() {
        viewModelScope.launch {
            _productDetailsEvents.emit(ProductDetailsContract.ProductDetailsEvents.NavigateToCart)
        }
    }

    private fun addProductToCart(productId: String, quantity: Int) {
        viewModelScope.launch(ioDispatcher) {
            _addToCartStates.emit(ProductDetailsContract.AddToCartStates.Loading)
            try {
                val response = addProductToCartUseCase(token, AddToCartRequest(productId))
                _addToCartStates.emit(ProductDetailsContract.AddToCartStates.Success(response!!))
                eventChannel.send(ProductDetailsContract.ProductDetailsEvents.ShowSuccess)
            } catch (e: Exception) {
                _addToCartStates.emit(
                    ProductDetailsContract.AddToCartStates.Error(
                        e.localizedMessage ?: "error"
                    )
                )
                eventChannel.send(ProductDetailsContract.ProductDetailsEvents.ShowError)
            }
            if (quantity != 1)
                updateCartProductQuantity(productId, quantity)
        }
    }

    private fun updateCartProductQuantity(productId: String, quantity: Int) {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = updateCartProductQuantityUseCase(
                    token,
                    UpdateUserCartRequest(quantity),
                    productId,
                )
            } catch (e: Exception) {
                _addToCartStates.emit(
                    ProductDetailsContract.AddToCartStates.Error(
                        e.localizedMessage ?: "error"
                    )
                )
            }
        }
    }

    private fun addProductToWishList(productId: String) {
        TODO("Not yet implemented")
    }

    private fun loadProduct(productId: String) {
        viewModelScope.launch(ioDispatcher) {
            getSpecificProductUseCase.invoke(productId).collect {
                when (it) {
                    is ResultWrapper.Loading -> {
                        _productDetailsStates.emit(ProductDetailsContract.ProductDetailsStates.Loading)
                    }

                    is ResultWrapper.Error -> {
                        _productDetailsStates.emit(
                            ProductDetailsContract.ProductDetailsStates.Error(
                                it.error.message ?: "error"
                            )
                        )
                    }

                    is ResultWrapper.Success -> {
                        _productDetailsStates.emit(
                            ProductDetailsContract.ProductDetailsStates.Success(
                                it.data
                            )
                        )
                    }

                    is ResultWrapper.ServerError -> {
                        _productDetailsStates.emit(
                            ProductDetailsContract.ProductDetailsStates.Error(
                                it.error.message ?: "server error"
                            )
                        )
                    }
                }
            }
        }
    }
}
