package com.example.jet_ecommerce.ui.features.main.products

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.features.product.model.Product
import com.example.domain.features.product.usecase.GetProductsPagingUseCase
import com.example.jet_ecommerce.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsPagingUseCase: GetProductsPagingUseCase,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    //    private val _states: MutableStateFlow<ProductsContract.States> =
//        MutableStateFlow(ProductsContract.States.Loading)
//    private val _events: MutableStateFlow<ProductsContract.Events> =
//        MutableStateFlow(ProductsContract.Events.Idle)
//    override val states = _states
//    override val events = _events
    private var _productState: MutableStateFlow<PagingData<Product>> =
        MutableStateFlow(value = PagingData.empty())
    val productState: MutableStateFlow<PagingData<Product>> = _productState

    init {
        val categoryId = savedStateHandle.get<String>("category_id")
        viewModelScope.launch(ioDispatcher) {
            getProductsPagingUseCase.invoke(categoryId).cachedIn(viewModelScope).collect {
                _productState.value = it
            }
        }

//        invokeAction(ProductsContract.Action.LoadProducts(categoryId))
    }

//    override fun invokeAction(action: ProductsContract.Action) {
//        when (action) {
//            is ProductsContract.Action.LoadProducts -> {}
////                loadProducts(action.categoryId)
//            is ProductsContract.Action.AddToCartButtonClick -> {}
//            is ProductsContract.Action.AddToWishListButtonClick -> {}
//            is ProductsContract.Action.ProductClick -> {}
//        }
//    }

//    private fun loadProducts(categoryId:String?) {
//        viewModelScope.launch(Dispatchers.IO) {
//            getProductsUseCase.invoke(categoryId).cachedIn(viewModelScope).collect() {
//                when (it) {
//
//                    is ResultWrapper.Success -> {
//                        _states.emit(ProductsContract.States.Success(it))
//                    }
//
//                    is ResultWrapper.ServerError -> {
//                        _states.emit(ProductsContract.States.Error(it.error.message))
//                    }
//
//                    is ResultWrapper.Error -> {
//                        _states.emit(ProductsContract.States.Error(it.error.message))
//                    }
//
//                    is ResultWrapper.Loading -> {
//                        _states.emit(ProductsContract.States.Loading)
//                    }
//                }
//            }
//        }
//    }


}