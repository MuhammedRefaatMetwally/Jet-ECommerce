package com.example.data.model

import com.google.gson.annotations.SerializedName

open class BaseResponse<T>(
    @field:SerializedName("status")
    val statusMsg: String? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName(value = "data" , alternate = ["session"])
    val data: T? = null,

)