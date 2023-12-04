package com.example.domain.features.cart.model

import com.example.domain.features.products.model.Product
import com.google.gson.annotations.SerializedName

data class CartResponse(

	@field:SerializedName("data")
	val cart: Cart? = null,

	@field:SerializedName("numOfCartItems")
	val numOfCartItems: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ProductsItem(

	@field:SerializedName("product")
	val product: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null
)

data class Cart(

	@field:SerializedName("cartOwner")
	val cartOwner: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("totalCartPrice")
	val totalCartPrice: Int? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
