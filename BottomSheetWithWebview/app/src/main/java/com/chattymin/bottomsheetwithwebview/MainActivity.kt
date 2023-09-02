package com.chattymin.bottomsheetwithwebview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.chattymin.bottomsheetwithwebview.AndroidKeyStoreUtil.decrypt
import com.chattymin.bottomsheetwithwebview.AndroidKeyStoreUtil.encrypt
import com.chattymin.bottomsheetwithwebview.ui.theme.BottomSheetWithWebviewTheme
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.AccessController.getContext
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomSheetWithWebviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    var test = AndroidKeyStoreUtil
                    test.init(this)
                    //Testing()

                    
                    var postcode = remember { mutableStateOf("") }
                    var home = remember { mutableStateOf("") }

                    var text1 = "hi hello"
                    var encodingText1 = test.encrypt(text1)
                    var decodingText1 = test.decrypt(encodingText1)
                    Column() {
                        Text(text = "암호화 전 : $text1")
                        Text(text = "암호화 후 : $encodingText1")
                        Text(text = "복호화 후 : $decodingText1")

                        /*
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = postcode.value, readOnly = true, onValueChange = {

                        })
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = home.value, readOnly = true, onValueChange = {

                        })
                        Button(onClick = {
                            searchAddress("창훈로 60번길 22-17", postcode, home)
                        }) {
                            Text(text = "주소검색")
                        }

                         */
                    }


                }
            }
        }
    }
}

private fun searchAddress(query: String, postcode: MutableState<String>, home1: MutableState<String>) {
    val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://naveropenapi.apigw.ntruss.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    retrofit.create(NaverMapApi::class.java).searchAddress(query)?.enqueue(object : Callback<AddressResponse?> {
        override fun onResponse(call: Call<AddressResponse?>, response: Response<AddressResponse?>) {
            val post = response.body().toString()
            val parts = post.split("\n")
            val address = parts[0]
            val postCode = parts[1]
            Log.e("res", response.body().toString())
            home1.value = address // 주소 텍스트뷰에 도로명주소
            postcode.value = postCode // 우편번호 텍스트뷰에 우편번호
        }

        override fun onFailure(call: Call<AddressResponse?>, t: Throwable) {
            // Handle failure
        }
    })

}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Testing() {
    var address = remember { mutableStateOf("open") }
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true,
    )
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth(),
            ) {
                CLabWebview(address){
                    coroutineScope.launch {
                            modalSheetState.hide()
                    }
                }
            }
        }
    ) {
        Scaffold {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            if (modalSheetState.isVisible)
                                modalSheetState.hide()
                            else
                                modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    },
                ) {
                    Text(text = address.value)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CLabWebview(address: MutableState<String>, onClick: ()->Unit){
    var webView: WebView? = null
    val URL = "http://10.0.2.2:8080/"

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {

                    }
                }
                settings.javaScriptEnabled = true

                loadUrl(URL)
                webView = this
            }
        }, update = {
            webView = it
        })

    BackHandler(enabled = true) {
        onClick()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BottomSheetWithWebviewTheme {
        Testing()
    }
}