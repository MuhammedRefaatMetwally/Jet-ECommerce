package com.example.data.api


import com.example.data.model.BaseResponse
import com.example.domain.features.category.model.Category
import com.example.domain.features.products.model.Product

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WebServices {
    @GET("api/v1/categories")
    suspend fun  getCategories(@Query("page") page : Int = 1) : BaseResponse<List<Category?>?>

    @GET("/api/v1/products")
    suspend fun getProducts() : BaseResponse<List<Product?>?>

    @POST("api/v1/auth/refreshToken")
     fun refreshToken(oldToken : String): Call<BaseResponse<String>>
}