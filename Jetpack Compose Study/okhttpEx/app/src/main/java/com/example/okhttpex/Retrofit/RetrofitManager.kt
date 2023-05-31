package com.example.okhttpex.Retrofit

import android.util.Log
import com.example.gd.Retrofit.IRetrofit
import com.example.gd.Retrofit.RetrofitClient
import com.example.okhttpex.API
import com.example.okhttpex.Constants.TAG
import com.example.okhttpex.Model.Photo
import com.example.okhttpex.RESPONSE_STATE
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat

class RetrofitManager {
    companion object{
        val instance = RetrofitManager()
    }

    // 레트로핏 인터페이스 가져오기
    private val iRetrofit: IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    // 사진 검색 api 호출
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATE, ArrayList<Photo>?) -> Unit){

        // searchTerm이 비어있으면 공백으로, 아니면 그대로 반환
        val term = searchTerm ?: ""

        val call = iRetrofit?.searchPhotos(searchTerm = term) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement>{
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL, null)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / respose : ${response.body()}")

                when(response.code()){
                    200 -> { // 정상 연결

                        response.body()?.let {
                            var parsedPhotoDAtaArray = ArrayList<Photo>()
                            val body = it.asJsonObject
                            val results = body.getAsJsonArray("results")

                            results.forEach{resultItem ->
                                val resultItemObject = resultItem.asJsonObject

                                val user = resultItemObject.get("user").asJsonObject
                                val username = user.get("username").asString
                                val likesCount = resultItemObject.get("likes").asInt
                                val thumubnailLink = resultItemObject.get("urls").asJsonObject.get("thumb").asString
                                val createdAt = resultItemObject.get("created_at").asString

                                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                val formatter = SimpleDateFormat("yyyy년 MM월 dd")

                                val outputDateString = formatter.format(parser.parse(createdAt))


                                val photoItem = Photo(
                                    author = username,
                                    likesCount = likesCount,
                                    thumbnail = thumubnailLink,
                                    createdAt = outputDateString
                                )

                                parsedPhotoDAtaArray.add(photoItem)
                            }

                            completion(RESPONSE_STATE.OKAY, parsedPhotoDAtaArray)
                        }

                    }
                }

            }
        })

    }
}