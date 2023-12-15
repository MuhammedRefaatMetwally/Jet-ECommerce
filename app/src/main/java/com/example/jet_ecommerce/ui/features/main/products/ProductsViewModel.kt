package com.example.jet_ecommerce.ui.features.main.products

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.features.products.model.Product
import com.example.domain.features.products.usecase.GetProductsPagingUseCase
import com.example.jet_ecommerce.IoDispatcher
import com.example.jet_ecommerce.ui.features.main.wishlist.WishListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  ProductsViewModel @Inject constructor(
    private val getProductsPagingUseCase: GetProductsPagingUseCase,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), ProductsContract.ViewModel {

    private val _states: MutableStateFlow<ProductsContract.States> =
        MutableStateFlow(ProductsContract.States.Loading)
    private val _events: MutableStateFlow<ProductsContract.Events> =
        MutableStateFlow(ProductsContract.Events.Idle)
    override val states: StateFlow<ProductsContract.States> = _states
    override val events: StateFlow<ProductsContract.Events> = _events

    private var productState: MutableStateFlow<PagingData<Product>> =
        MutableStateFlow(value = PagingData.empty())

    init {
        val categoryId = savedStateHandle.get<String>("category_id")
        invokeAction(ProductsContract.Action.LoadProducts(categoryId))

    }

    override fun invokeAction(action: ProductsContract.Action) {
        when (action) {
            is ProductsContract.Action.LoadProducts -> { loadProducts(action.categoryId) }
            is ProductsContract.Action.ProductClick -> { productClick(action.productId!!) }
            is ProductsContract.Action.AddToCartButtonClick -> {}
            is ProductsContract.Action.AddToWishListButtonClick -> {}

        }
    }

    private fun productClick(productId: String) {
        viewModelScope.launch {
            _events.emit(ProductsContract.Events.NavigateToProductDetailsScreen(productId))
        }

    }


    private fun loadProducts(categoryId: String?) {
        viewModelScope.launch(ioDispatcher) {
            _states.emit(ProductsContract.States.Loading)
            try {
                getProductsPagingUseCase.invoke(categoryId).cachedIn(viewModelScope).collect() {
                    productState.value = it
                    _states.emit(ProductsContract.States.Success(productState))
                }
            } catch (e: Exception) {
                _states.emit(ProductsContract.States.Error(e.message))
            }
        }
    }
}