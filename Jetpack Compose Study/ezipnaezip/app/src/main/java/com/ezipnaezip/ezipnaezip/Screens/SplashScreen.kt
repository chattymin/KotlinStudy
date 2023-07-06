package com.ezipnaezip.ezipnaezip.Screens

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ezipnaezip.ezipnaezip.App
import com.ezipnaezip.ezipnaezip.R
import com.ezipnaezip.ezipnaezip.Retrofit.RetrofitManager
import com.ezipnaezip.ezipnaezipn.navigation.Screen
import com.ezipnaezip.gd.Utils.Constants
import com.ezipnaezip.gd.Utils.MESSAGE
import com.ezipnaezip.gd.Utils.RESPONSE_STATE

@Composable
fun SplashScreen(navController: NavHostController) {
    //navController.enableOnBackPressed(false)
    var test by remember { mutableStateOf(true) } // 함수가 두번 호출돼서 화면 깜빡이는 것 방지

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background) // 로딩창 배경생
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.mainlogo_white),
                contentDescription = "logo",
                modifier = Modifier
                    .size(200.dp)
            )
        }

//        if (OAuthData.account == null) {
//            if (test) {
//                Handler(Looper.getMainLooper()).postDelayed({
//                    test = false
//                    // 로그인 안돼있음
//                    navController.navigate(Screen.Login.route)
//                }, 1000)
//            }
//        } else {
//            if (test) {
//                Handler(Looper.getMainLooper()).postDelayed({
//                    test = false
//                    // 로그인 돼있음
//                    navController.navigate(Screen.Once.route)
//                }, 1000)
//            }
//        }

//        if (test) {
//            Handler(Looper.getMainLooper()).postDelayed({
//                test = false
//                // 로그인 돼있음
//                navController.navigate(Screen.Once.route)
//
//            }, 1000)
//        }


        if (test) {
            test = false
            Handler(Looper.getMainLooper()).postDelayed({
                if (OAuthData.account == null) {
                    // 로그인 안돼있음
                    navController.navigate(Screen.Login.route)
                } else {
                    // 로그인 돼있음
                    val uid = OAuthData.auth?.currentUser?.uid ?: ""
                    RetrofitManager.instance.firebaseConnect(uid, completion = {
                            responseState ->

                        when (responseState) {
                            RESPONSE_STATE.OKAY -> {
                                OAuthData.nav?.navigate(Screen.Once.route)
                                Log.d(Constants.TAG, "api 호출 성공")
                            }
                            RESPONSE_STATE.FAIL -> {
                                Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                                Log.d(Constants.TAG, "api 호출 에러")
                            }
                        }
                    })
                }
            }, 1000)
        }

    }
}