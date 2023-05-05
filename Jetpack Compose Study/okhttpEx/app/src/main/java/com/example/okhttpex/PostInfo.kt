package com.example.okhttpex

import com.google.gson.annotations.SerializedName

data class PostInfo(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("data")
    val data: PostInfoData
    )

data class PostInfoData(
    @SerializedName("content")
    val content: String,

    @SerializedName("create_ad")
    val create_ad: String,

    @SerializedName("like_count")
    val like_count: Int,

    @SerializedName("bookmark")
    val bookmark: Array<String>,

    @SerializedName("likes")
    val likes: Array<String>,

    @SerializedName("follow")
    val follow: Array<String>,

    @SerializedName("is_me")
    val is_me: Boolean,

    @SerializedName("profile_img_url")
    val profile_img_url: String,

    @SerializedName("tag_id")
    val tag_id: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostInfoData

        if (content != other.content) return false
        if (create_ad != other.create_ad) return false
        if (like_count != other.like_count) return false
        if (!bookmark.contentEquals(other.bookmark)) return false
        if (!likes.contentEquals(other.likes)) return false
        if (!follow.contentEquals(other.follow)) return false
        if (is_me != other.is_me) return false
        if (profile_img_url != other.profile_img_url) return false
        if (tag_id != other.tag_id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = content.hashCode()
        result = 31 * result + create_ad.hashCode()
        result = 31 * result + like_count
        result = 31 * result + bookmark.contentHashCode()
        result = 31 * result + likes.contentHashCode()
        result = 31 * result + follow.contentHashCode()
        result = 31 * result + is_me.hashCode()
        result = 31 * result + profile_img_url.hashCode()
        result = 31 * result + tag_id
        return result
    }
}