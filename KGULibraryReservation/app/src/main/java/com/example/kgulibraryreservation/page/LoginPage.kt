package com.example.kgulibraryreservation.page

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.*
import com.example.kgulibraryreservation.R

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun LoginScreen() {
    val jsonObject = JSONObject()

    val image = painterResource(R.drawable.kgulogo)
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    jsonObject.put(id,"id")
    jsonObject.put(password,"password")
    val mediaType = "application/json; charset=utf-8".toMediaType()
    val requestbody = jsonObject.toString().toRequestBody(mediaType)


    val context = LocalContext.current
    val url = "https://library.kyonggi.ac.kr/login?retUrl=/relation/seat?"

    Scaffold(
        backgroundColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = image,
                contentDescription = "My Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            OutlinedTextField(
                value = id,
                onValueChange = { id = it },
                label = { Text(text = "학번") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "비밀번호") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Button(
                onClick = {
                    postApiRequest(requestbody)
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Log in")
            }
            Text(
                text = "아이디와 비밀번호를 까먹으셨나요?",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            TextButton(
                onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "찾으러 가기", color = MaterialTheme.colors.primary)
            }
        }
    }
}

// POST 요청할 URL
const val URL = "https://clab.page/api/docs"

fun postApiRequest(requestBody: RequestBody) {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url(URL)
        .post(requestBody)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // API 요청 실패 시 호출될 함수
            println("API Request Failed: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            // API 요청 성공 시 호출될 함수
            println("API Response: ${response.body?.string()}")
        }
    })
}

class Info(var personalNumber: String, var password: String)

