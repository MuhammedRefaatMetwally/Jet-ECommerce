package com.example.jet_ecommerce.ui.features.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.features.category.usecase.GetCategoriesUseCase
import com.example.domain.features.product.usecase.GetProductsUseCase
import com.example.jet_ecommerce.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoriesUseCase: GetCategoriesUseCase,
    private val productsUseCase: GetProductsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), HomeContract.ViewModel , ProductContract.ViewModel{

    private val _categoriesStates = MutableStateFlow<HomeContract.State>(
        HomeContract.State.Loading
    )
    override val categoriesStates: StateFlow<HomeContract.State>
        get() = _categoriesStates

    private val _productsStates = MutableStateFlow<ProductContract.State>(
        ProductContract.State.Loading
    )

    override val productsStates: StateFlow<ProductContract.State>
        get() = _productsStates



    private val _categoriesEvents = MutableLiveData<HomeContract.Event>()

    override val categoriesEvents: LiveData<HomeContract.Event>
        get() = _categoriesEvents

    private val _productEvents = MutableLiveData<ProductContract.Event>()
    override val productEvents: LiveData<ProductContract.Event>
        get() = _productEvents



    override fun invokeCategoriesAction(action: HomeContract.Action) {
        when (action) {

            is HomeContract.Action.LoadCategories -> loadCategories()
            is HomeContract.Action.CategoryClick -> {
                _categoriesEvents.postValue(HomeContract.Event.NavigateToSubCategories(action.category))
            }

        }
    }

    override fun invokeProductsAction(action: ProductContract.Action) {
        when (action) {

            is ProductContract.Action.LoadProducts -> loadProducts()
            is ProductContract.Action.CategoryClick -> {
                _productEvents.postValue(ProductContract.Event.NavigateToProductDetails(action.product))
            }

        }
    }



    private fun loadCategories() {
        viewModelScope.launch(ioDispatcher) {
            categoriesUseCase.invoke().collect { response ->
                when (response) {

                    is ResultWrapper.Success -> {
                        _categoriesStates.emit(HomeContract.State.CategorySuccess(response.data))
                    }

                    is ResultWrapper.ServerError -> {
                        _categoriesStates.emit(HomeContract.State.Error(response.error.serverMessage))
                    }

                    is ResultWrapper.Error -> {
                        _categoriesStates.emit(HomeContract.State.Error(response.error.localizedMessage ?: ""))
                    }

                    is ResultWrapper.Loading -> {
                        _categoriesStates.emit(HomeContract.State.Loading)
                    }
                }

            }
        }
    }

    private fun loadProducts() {
      viewModelScope.launch(ioDispatcher) {
          productsUseCase.invoke().collect{ response ->
              when (response) {

                  is ResultWrapper.Success -> {
                      _productsStates.emit(ProductContract.State.ProductSuccess(response.data))
                  }

                  is ResultWrapper.ServerError -> {
                      _productsStates.emit(ProductContract.State.Error(response.error.serverMessage))
                  }

                  is ResultWrapper.Error -> {
                      _productsStates.emit(ProductContract.State.Error(response.error.localizedMessage ?: ""))
                  }

                  is ResultWrapper.Loading -> {
                      _productsStates.emit(ProductContract.State.Loading)
                  }
              }

          }
      }
    }
}