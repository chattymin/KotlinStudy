package com.example.okhttpex

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userId")
    val userId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("body")
    val body: String
)
// @SerializedName("변수명") -> 이게 있으면 변수명과 클래스 변수명이 달라도 됨.