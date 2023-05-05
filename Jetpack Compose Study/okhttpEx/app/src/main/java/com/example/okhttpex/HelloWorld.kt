package com.example.okhttpex

import com.google.gson.annotations.SerializedName

data class HelloWorld(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("data")
    val data: String,
)
