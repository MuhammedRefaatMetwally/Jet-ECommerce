package com.example.data.api


import com.example.data.model.BaseResponse
import com.example.domain.features.category.model.Category
import com.example.domain.features.products.model.Product
import com.example.domain.features.subCategories.model.SubCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
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
        @Query("page") pageNumber: Int ?=null
    ): BaseResponse<List<Product>>

    @POST("api/v1/auth/refreshToken")
    fun refreshToken(oldToken: String): Call<BaseResponse<String>>
}