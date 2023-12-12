package com.example.domain.features.cart.model.check_out

data class CreateCastOrderResponse(
	val data: CashOrderData? = null,
	val status: String? = null
)

data class CashOrderData(
	val totalOrderPrice: Int? = null,
	val isPaid: Boolean? = null,
	val isDelivered: Boolean? = null,
	val createdAt: String? = null,
	val shippingPrice: Int? = null,
	val v: Int? = null,
	val taxPrice: Int? = null,
	val shippingAddress: ShippingAddress? = null,
	val id: String? = null,
	val id2: Int? = null,
	val cartItems: List<CartItemsItem?>? = null,
	val paymentMethodType: String? = null,
	val user: String? = null,
	val updatedAt: String? = null
)

data class ShippingAddress(
	val phone: String? = null,
	val city: String? = null,
	val details: String? = null
)

data class CartItemsItem(
	val product: String? = null,
	val price: Int? = null,
	val count: Int? = null,
	val id: String? = null
)

