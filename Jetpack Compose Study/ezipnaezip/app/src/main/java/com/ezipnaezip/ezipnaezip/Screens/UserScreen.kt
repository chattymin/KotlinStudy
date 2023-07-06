package com.ezipnaezip.ezipnaezip.Screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ezipnaezip.ezipnaezip.App
import com.ezipnaezip.ezipnaezip.Button.BackButton
import com.ezipnaezip.ezipnaezip.Effects.UserMyContent
import com.ezipnaezip.ezipnaezip.Model.Post
import com.ezipnaezip.ezipnaezip.Model.User
import com.ezipnaezip.ezipnaezip.Retrofit.RetrofitManager
import com.ezipnaezip.gd.Utils.Constants
import com.ezipnaezip.gd.Utils.MESSAGE
import com.ezipnaezip.gd.Utils.RESPONSE_STATE

val otherbookmarkArr = arrayListOf<Post>()
val othermyArr = arrayListOf<Post>()
var otherinfo = User()

@SuppressLint("UnrememberedMutableState")
@Composable
fun UserScreen(navController: NavController, route: String, userId: Int) {
    var loading by rememberSaveable{ mutableStateOf(true) }
    var isOkay by rememberSaveable{ mutableStateOf(false) }

    if (loading) {
        loading = false
        otherbookmarkArr.clear()
        othermyArr.clear()
        RetrofitManager.instance.userInfoUserid(
            userId,
            completion = { responseState, bookmarkArr, myArr, info ->

                when (responseState) {
                    RESPONSE_STATE.OKAY -> {
                        Log.d(Constants.TAG, "api 호출 성공")
                        otherbookmarkArr.addAll(bookmarkArr!!)
                        othermyArr.addAll(myArr!!)
                        otherinfo = info!!
                        isOkay = true
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                        Log.d(Constants.TAG, "api 호출 에러")
                    }
                }
            })
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            BackButton(navController)
        }
        UserMyContent(navController = navController, route = route, otherbookmarkArr, othermyArr, userInfo = otherinfo)
    }
}