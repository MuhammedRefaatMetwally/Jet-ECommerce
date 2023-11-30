package com.example.domain.features.cart.model

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("data")
    val cart: Cart? = null,
    val numOfCartItems: Int? = null,
    val message: String? = null,
    val status: String? = null
)

data class Cart(
    val cartOwner: String? = null,
    val createdAt: String? = null,
    val totalCartPrice: Int? = null,
    val v: Int? = null,
    val id: String? = null,
    val products: List<ProductsItem?>? = null,
    val updatedAt: String? = null
)

data class ProductsItem(
    val product: String? = null,
    val price: Int? = null,
    val count: Int? = null,
    val id: String? = null
)
