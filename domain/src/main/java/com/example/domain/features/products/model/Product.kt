package com.example.domain.features.products.model

import android.os.Parcelable
import com.example.domain.features.brand.model.Brand
import com.example.domain.features.category.model.Category
import com.example.domain.features.subCategories.model.SubCategory
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val sold: Int? = null,
    val images: List<String?>? = null,
    val quantity: Int? = null,
    val imageCover: String? = null,
    val description: String? = null,
    val title: String? = null,
    val ratingsQuantity: Int? = null,
    val ratingsAverage: Double? = null,
    val createdAt: String? = null,
    val price: Int? = null,
    val id: String? = null,
    val subcategory: List<SubCategory?>? = null,
    val category: Category? = null,
    val brand: Brand? = null,
    val slug: String? = null,
    val updatedAt: String? = null
) : Parcelable



