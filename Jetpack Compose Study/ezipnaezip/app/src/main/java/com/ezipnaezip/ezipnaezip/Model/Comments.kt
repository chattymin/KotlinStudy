package com.ezipnaezip.ezipnaezip.Model

data class Comments(
    val serialNum: Int,
    val userId: Int,
    val name: String,
    val profileImgUrl: String,
    val content: String,
    val isMe: Boolean
)
