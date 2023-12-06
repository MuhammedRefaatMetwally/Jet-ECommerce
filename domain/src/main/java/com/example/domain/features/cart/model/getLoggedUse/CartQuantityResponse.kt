package com.example.domain.features.cart.model.getLoggedUse

import com.example.domain.features.products.model.Product
import com.google.gson.annotations.SerializedName

data class CartQuantityResponse(

    @field:SerializedName("data")
    val cart: CartQuantity? = null,

    @field:SerializedName("numOfCartItems")
    val numOfCartItems: Int? = null,

    @field:SerializedName("status")
    val status: String? = null
)


data class CartQuantity(

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
    val products: List<ProductItem?>? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)

data class ProductItem(

    @field:SerializedName("product")
    val product: Product? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null
)
