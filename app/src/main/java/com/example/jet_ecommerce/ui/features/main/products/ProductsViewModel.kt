package com.example.jet_ecommerce.ui.features.main.products

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.features.product.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(), ProductsContract.ViewModel {

    private val _states: MutableStateFlow<ProductsContract.States> =
        MutableStateFlow(ProductsContract.States.Loading)
    private val _events: MutableStateFlow<ProductsContract.Events> =
        MutableStateFlow(ProductsContract.Events.Idle)

    override val states = _states
    override val events = _events

    init {
        val categoryId = savedStateHandle.get<String>("category_id")
        invokeAction(ProductsContract.Action.LoadProducts(categoryId))
    }

    override fun invokeAction(action: ProductsContract.Action) {
        when (action) {
            is ProductsContract.Action.LoadProducts -> loadProducts()
            is ProductsContract.Action.AddToCartButtonClick -> {}
            is ProductsContract.Action.AddToWishListButtonClick -> {}
            is ProductsContract.Action.ProductClick -> {}
        }
    }

    private fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase.invoke().collect {
                when (it) {

                    is ResultWrapper.Success -> {
                        _states.emit(ProductsContract.States.Success(it.data))
                    }

                    is ResultWrapper.ServerError -> {
                        _states.emit(ProductsContract.States.Error(it.error.message))
                    }

                    is ResultWrapper.Error -> {
                        _states.emit(ProductsContract.States.Error(it.error.message))
                    }

                    is ResultWrapper.Loading -> {
                        _states.emit(ProductsContract.States.Loading)
                    }
                }
            }
        }
    }
}