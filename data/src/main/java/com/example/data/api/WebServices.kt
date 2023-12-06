package com.example.data.api


import com.example.data.model.BaseResponse
import com.example.domain.features.cart.model.addToCart.AddToCartRequest
import com.example.domain.features.cart.model.getLoggedUse.CartQuantity
import com.example.domain.features.cart.model.getLoggedUse.CartQuantityResponse

import com.example.domain.features.cart.model.addToCart.CartResponse
import com.example.domain.features.cart.model.updateUserCart.UpdateUserCartRequest
import com.example.domain.features.category.model.Category
import com.example.domain.features.login.model.LoginEntity
import com.example.domain.features.login.model.LoginRequest
import com.example.domain.features.login.model.LoginResponse
import com.example.domain.features.products.model.Product
import com.example.domain.features.register.model.RegisterRequest
import com.example.domain.features.register.model.RegisterResponse
import com.example.domain.features.subCategories.model.SubCategory
import com.example.domain.features.wishlist.model.WishListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServices {
    @GET("api/v1/categories")
    suspend fun getCategories(
        @Query("page") page: Int = 1
    ): BaseResponse<List<Category?>?>

    @GET("/api/v1/categories/{category_id}/subcategories")
    suspend fun getSubCategoriesOnCategory(
        @Path("category_id") categoryId: String
    ): BaseResponse<List<SubCategory>>

    @GET("/api/v1/products")
    suspend fun getProductsList(
        @Query("category[in]") categoryId: String? = null,
//        @Query("limit") productsPerPage: Int = 8,
        @Query("page") pageNumber: Int? = null
    ): BaseResponse<List<Product>>

    @GET("/api/v1/products/{product_id}")
    suspend fun getSpecificProduct(
        @Path("product_id") productId: String
    ): BaseResponse<Product>

    @POST("api/v1/auth/refreshToken")
    fun refreshToken(oldToken: String): Response<LoginEntity>


    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/signup")
    suspend fun registration(@Body registerRequest: RegisterRequest): RegisterResponse

    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/signin")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @Headers("Content-Type: application/json")
    @POST("api/v1/cart")
    suspend fun addProductToCart(
        @Header("token") token: String,
        @Body addToCartRequest: AddToCartRequest
    ): CartResponse

    @Headers("Content-Type: application/json")
    @PUT("/api/v1/cart/{product_id}")
    suspend fun updateCartProductQuantity(
        @Header("token") token: String,
        @Body updateUserCartRequest: UpdateUserCartRequest,
        @Path("product_id") productId: String
    ): CartQuantityResponse

    @Headers("Content-Type: application/json")
    @GET("api/v1/cart")
    suspend fun getLoggedUserCart(@Header("token") token: String): BaseResponse<CartQuantity>

    @Headers("Content-Type: application/json")
    @POST("api/v1/wishlist")
    suspend fun addProductToWishList(
        @Header("token") token: String,
        @Body productId: String
    ): WishListResponse

    @Headers("Content-Type: application/json")
    @GET("/api/v1/wishlist")
    suspend fun getLoggedUserWishList(@Header("token") token: String): BaseResponse<List<Product>>


    @DELETE("api/v1/cart/{product_id}")
    suspend fun deleteSpecificCartItem(
        @Header("token") token: String,
        @Path("product_id") productId: String
    ): BaseResponse<Any>

    @DELETE("api/v1/cart")
    suspend fun clearCart(@Header("token") token: String): BaseResponse<Any>

}