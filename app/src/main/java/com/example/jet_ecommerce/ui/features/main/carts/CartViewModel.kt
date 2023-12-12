package com.example.jet_ecommerce.ui.features.main.carts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.TokenManager
import com.example.domain.common.ResultWrapper
import com.example.domain.features.cart.model.ShoppingAddingRequest
import com.example.domain.features.cart.model.check_out.Session
import com.example.domain.features.cart.model.getLoggedUse.CartQuantity
import com.example.domain.features.cart.model.getLoggedUse.ProductItem
import com.example.domain.features.cart.model.updateUserCart.UpdateUserCartRequest
import com.example.domain.features.cart.usecase.cart.ClearCartUseCase
import com.example.domain.features.cart.usecase.cart.DeleteSpecificCartItemUseCase
import com.example.domain.features.cart.usecase.cart.GetLoggedUserCartUseCase
import com.example.domain.features.cart.usecase.cart.UpdateCartProductQuantityUseCase
import com.example.domain.features.cart.usecase.checkout.CartCheckOutUseCase
import com.example.domain.features.cart.usecase.checkout.CreateCastOrderUseCase
import com.example.jet_ecommerce.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val getLoggedUserCartUseCase: GetLoggedUserCartUseCase,
    private val deleteSpecificCartItem: DeleteSpecificCartItemUseCase,
    private val updateCartProductQuantityUseCase: UpdateCartProductQuantityUseCase,
    private val clearCartUseCase : ClearCartUseCase,
    private val checkOutUseCase: CartCheckOutUseCase,
    private val createCheckOutUseCase: CreateCastOrderUseCase,
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

     var cartProducts = mutableListOf<ProductItem?>()
    var totalPrice = 0;
    var cartProductsSize : Int? = 0

    private val eventChannel = Channel<CartContract.Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()//single live event
    init {

        viewModelScope.launch {
            tokenManager.getToken().collect { token = it!! }
        }
    }

    override fun invokeAction(action: CartContract.Action) {
        when (action) {
            CartContract.Action.GetUserProducts -> getUserProducts()
            CartContract.Action.ClearCart -> clearCart()

            is CartContract.Action.DeleteSpecificCartItem -> {
               deleteSpecificItem(action.productId, action.product)

            }

            is CartContract.Action.UpdateCartProductQuantity -> updateCartProductQuantity(
                count = action.count,
                productId = action.productId
            )

            is CartContract.Action.CheckOut -> checkOutProduct(action.userId)
            is CartContract.Action.CreateCastOrder -> createCastOrder(action.userId,action.shoppingAddingRequest)
            else -> {}
        }
    }

    private fun createCastOrder(userId: String, shoppingAddingRequest: ShoppingAddingRequest) {
        viewModelScope.launch(ioDispatcher) {
            createCheckOutUseCase.invoke(token,userId,shoppingAddingRequest).collect{
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
                        _states.emit(CartContract.State.Success(createCheckoutData = it.data))

                    }

                    else -> {}
                }
            }
        }
    }

    private fun checkOutProduct(userId : String) {
        viewModelScope.launch(ioDispatcher) {
            checkOutUseCase.invoke(token,userId).collect{
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
                        _states.emit(CartContract.State.Success(checkOutData = it.data))
                        eventChannel.send(CartContract.Event.NavigateToStripe)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun updateCartProductQuantity(count : Int , productId: String) {
        viewModelScope.launch(ioDispatcher) {
            updateCartProductQuantityUseCase.invoke(token = token, UpdateUserCartRequest(
                count = count
            ),productId)
            getUserProducts()
        }
    }


    private fun deleteSpecificItem(productId: String, product: ProductItem?) {
        viewModelScope.launch(ioDispatcher) {
            if(cartProducts.size>=1){
                cartProducts.remove(product)
                deleteSpecificCartItem.invoke(token, productId = productId)
                _states.emit(CartContract.State.Loading)
                getUserProducts()
                eventChannel.send(CartContract.Event.ShowSuccess)
            }else{
                eventChannel.send(CartContract.Event.ShowError)
            }

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
                        cartProducts = it.data?.products?.toMutableList()!!
                        cartProductsSize = it.data?.products?.size ?: 0
                    }

                    else -> {}
                }
            }
        }

    }

}