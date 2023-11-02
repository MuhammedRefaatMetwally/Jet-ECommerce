package com.example.domain.features.category.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
val image : String? = null,
val createdAt : String? = null,
val name : String? = null,
@SerializedName("_id")
val id : String? = null,
val slug : String?  = null,
val updatedAt : String? = null
):Parcelable
