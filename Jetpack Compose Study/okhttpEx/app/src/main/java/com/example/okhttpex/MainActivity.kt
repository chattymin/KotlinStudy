package com.example.okhttpex

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.okhttpex.Constants.TAG
import com.example.okhttpex.Retrofit.RetrofitManager
import com.example.okhttpex.ui.theme.OkhttpExTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {

    private var currentSearchTypes: SEARCH_TYPE = SEARCH_TYPE.PHOTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OkhttpExTheme {
                
                /*
                val retrofit = Retrofit.Builder().baseUrl("http://api.ezipnaezip.life:8080/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
                val service = retrofit.create(RetrofitService::class.java)

                service.getInterior()?.enqueue(object : Callback<Interior> {
                    override fun onResponse(call: Call<Interior>, response: Response<Interior>) {
                        if(response.isSuccessful){
                            // 정상적으로 통신이 성고된 경우
                            var result: Interior? = response.body()
                            Log.d("YMC", "onResponse 성공: " + result?.toString())
                        }else{
                            // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                            Log.d("YMC", "onResponse 실패")
                        }
                    }
                    override fun onFailure(call: Call<Interior>, t: Throwable) {
                        // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                        Log.d("YMC", "onFailure 에러: " + t.message.toString());
                    }
                })
                
                 */


/*


                val retrofit = Retrofit.Builder().baseUrl("https://api.gwansik.dev/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
                val service = retrofit.create(RetrofitService::class.java)

                service.getPostInfo(1)?.enqueue(object : Callback<PostInfo> {
                    override fun onResponse(call: Call<PostInfo>, response: Response<PostInfo>) {
                        if(response.isSuccessful){
                            // 정상적으로 통신이 성고된 경우
                            var result: PostInfo? = response.body()
                            Log.d("YMC", "onResponse 성공: " + result?.toString())
                        }else{
                            // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                            Log.d("YMC", "onResponse 실패")
                        }
                    }
                    override fun onFailure(call: Call<PostInfo>, t: Throwable) {
                        // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                        Log.d("YMC", "onFailure 에러: " + t.message.toString());
                    }
                })



 */



/*
                val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
                val service = retrofit.create(RetrofitService::class.java);
                service.getUserPage("1")?.enqueue(object : Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if(response.isSuccessful){
                            // 정상적으로 통신이 성고된 경우
                            var result: User? = response.body()
                            Log.d("YMC", "onResponse 성공: " + result?.toString());
                        }else{
                            // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                            Log.d("YMC", "onResponse 실패")
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                        Log.d("YMC", "onFailure 에러: " + t.message.toString());
                    }
                })

 */




                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Button(onClick = {
                        RetrofitManager.instance.searchPhotos(searchTerm = "cat", completion = {
                            responseState, respnseBody ->

                            when(responseState){
                                RESPONSE_STATE.OKAY -> {
                                    Log.d(TAG, "api 호출 성공 : ${respnseBody?.size}")
                                }
                                RESPONSE_STATE.FAIL -> {
                                    Toast.makeText(this, "api 호출 에러", Toast.LENGTH_SHORT).show()
                                    Log.d(TAG, "api 호출 실패 : $respnseBody")
                                }
                            }
                        })
                    }) {
                        Text(text = "API 호출")
                    }
                }
            }
        }
    }
}

/*

val retrofit = Retrofit.Builder().baseUrl("https://api.gwansik.dev")
                    .addConverterFactory(GsonConverterFactory.create()).build()
                val service = retrofit.create(RetrofitService::class.java)

                service.getPostInfo("1")?.enqueue(object : Callback<PostInfo> {
                    override fun onResponse(call: Call<PostInfo>, response: Response<PostInfo>) {
                        if(response.isSuccessful){
                            // 정상적으로 통신이 성고된 경우
                            var result: PostInfo? = response.body()
                            Log.d("YMC", "onResponse 성공: " + result?.toString())
                        }else{
                            // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                            Log.d("YMC", "onResponse 실패")
                        }
                    }
                    override fun onFailure(call: Call<PostInfo>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
 */