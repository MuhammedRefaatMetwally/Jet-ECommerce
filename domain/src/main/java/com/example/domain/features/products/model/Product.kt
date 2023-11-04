package com.example.domain.features.products.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

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
	val subcategory: List<SubcategoryItem?>? = null,
	val category: Category? = null,
	val brand: Brand? = null,
	val slug: String? = null,
	val updatedAt: String? = null
) : Parcelable

@Parcelize
data class Category(
	val image: String? = null,
	val name: String? = null,
	val id: String? = null,
	val slug: String? = null
) : Parcelable

@Parcelize
data class SubcategoryItem(
	val name: String? = null,
	val id: String? = null,
	val category: String? = null,
	val slug: String? = null
) : Parcelable

@Parcelize
data class Brand(
	val image: String? = null,
	val name: String? = null,
	val id: String? = null,
	val slug: String? = null
) : Parcelable
