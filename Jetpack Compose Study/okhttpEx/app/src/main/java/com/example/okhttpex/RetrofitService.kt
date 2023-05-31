package com.example.okhttpex


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface RetrofitService {
    //GET 예제
    @GET("posts/1")
    fun getUser(): Call<User>

    @GET("posts/{page}")
    fun getUserPage(@Path("page") page: String): Call<User>

    @GET("Community/info/{post_num}")
    fun getPostInfo(@Path("post_num") post_num: Int): Call<PostInfo>

    @GET("")
    fun getHelloWorld(@Url url: String): Call<HelloWorld>
}