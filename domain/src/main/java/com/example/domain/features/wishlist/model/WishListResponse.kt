package com.example.domain.features.wishlist.model

import com.example.domain.features.products.model.Product
import com.google.gson.annotations.SerializedName

data class WishListResponse(
	@SerializedName("data")
	val products: List<Product?>? = null,
	val message:String?=null,
	val count: Int? = null,
	val status: String? = null
)




