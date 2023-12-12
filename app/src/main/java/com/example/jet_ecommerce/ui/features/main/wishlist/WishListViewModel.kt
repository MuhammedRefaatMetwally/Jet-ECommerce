package com.example.jet_ecommerce.ui.features.main.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.api.TokenManager
import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.addToCart.AddToCartRequest
import com.example.domain.features.products.model.Product
import com.example.domain.features.products.usecase.GetProductsPagingUseCase
import com.example.domain.features.wishlist.model.WishListResponse
import com.example.domain.features.wishlist.usecase.AddProductToWishListUseCase
import com.example.domain.features.wishlist.usecase.GetLoggedUserWishListUseCase
import com.example.domain.features.wishlist.usecase.RemoveProductFromWishListUseCase
import com.example.jet_ecommerce.IoDispatcher
import com.example.jet_ecommerce.ui.features.main.carts.CartContract
import com.example.jet_ecommerce.ui.features.main.products.ProductsContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WishListViewModel @Inject constructor(
    private val addProductToWishListUseCase: AddProductToWishListUseCase,
    private val removeProductFromWishList: RemoveProductFromWishListUseCase,
    private val getLoggedUserWishListUseCase: GetLoggedUserWishListUseCase,
    private val getProductsPagingUseCase: GetProductsPagingUseCase,
    private val tokenManager: TokenManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel(),
    WishListContract.ViewModel {

    private lateinit var token : String
    private val _states: MutableStateFlow<WishListContract.State> =
        MutableStateFlow(WishListContract.State.Idle)
    override val states: StateFlow<WishListContract.State>
        get() = _states

    private val _events : MutableStateFlow<WishListContract.Event> =
        MutableStateFlow(WishListContract.Event.Idle)
    override val events: StateFlow<WishListContract.Event>
        get() = _events

    private val eventChannel = Channel<WishListContract.Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()//single live event
    init {
        viewModelScope.launch {
            tokenManager.getToken().collect { token = it!! }
        }
    }
    override fun invokeAction(action: WishListContract.Action) {
        when(action){
            is WishListContract.Action.AddProductToWishLIst -> addProductToWishList(action.addToCartRequest)
            is WishListContract.Action.GetWishListProducts -> getWishListProducts()
            is WishListContract.Action.RemoveProductFomWishList -> removeProductFromWishList(action.productId)
        }
    }

    private fun addProductToWishList(addToCartRequest : AddToCartRequest) {
      viewModelScope.launch(ioDispatcher) {
          try {
              addProductToWishListUseCase.invoke(token,addToCartRequest)
          }catch (e: Exception)
          {
              if(e is IllegalStateException)
                  throw java.lang.IllegalStateException()
          }
      }
    }

    private fun getWishListProducts() {
          viewModelScope.launch(ioDispatcher) {
              getLoggedUserWishListUseCase.invoke(token).collect{
                  when(it){
                      is ResultWrapper.Error -> {
                          _states.emit(WishListContract.State.Error(it.error.message.toString()))
                      }
                      is ResultWrapper.Loading -> {
                          _states.emit(
                              WishListContract.State.Loading
                          )
                      }
                      is ResultWrapper.ServerError -> {
                          _states.emit(
                              WishListContract.State.Error(it.error.message.toString())
                          )
                      }
                      is ResultWrapper.Success ->{
                          _states.emit(WishListContract.State.Success(productData = it.data ?: emptyList(),data = WishListResponse() ))

                      }
                      null -> {

                      }
                  }
              }
          }
    }

    private fun removeProductFromWishList(productId : String) {
        viewModelScope.launch(ioDispatcher) {
            removeProductFromWishList.invoke(token, productId = productId)
        }
    }

     var productState: MutableStateFlow<PagingData<Product>> =
        MutableStateFlow(value = PagingData.empty())


}