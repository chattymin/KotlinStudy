package com.example.text

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.text.ui.theme.TextTheme

// 모든 내용을 setContent에 넣는 것은 좋지 않다.
// 왜? => Preview 기능을 활용하자. 매번 빌드하면서 확인하기엔 느리고, 비효율적이다.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    //Text(text = "Hello $name!")

    // color를 활용하여 색상을 넣어보자.
    //Text(color = Color.Red, text = "Hello $name!")

    // Color객체를 활용해서 해쉬값을 색상을 전달해보자.
    //Text(color = Color(0xffff99944), text = "Hello $name!")

    // fontSize를 30.sp를 설정해보자. => 글씨 크기
    //Text(fontSize = 30.sp, text = "Hello $name!")

    // fontWeight에 FontWeigth.Bold를 설정해보자. => 두께 조정
    //Text(fontWeight = FontWeight.Bold, text = "Hello $name!")

    // fontFamily에 FontFamily.Cursive를 설정해보자. => 폰트(글꼴)
    Text(fontFamily = FontFamily.Cursive, text = "Hello $name!")

    // letterSpacing에 2.sp를 설정해보자. => 글자사이 간격
    //Text(letterSpacing = 2.sp, text = "Hello $name!")

    // maxLine을 2로 지정하고, 문자열을 추가해보자.
    //Text(maxLines = 2, text = "Hello $name!\nHello $name!")

    // textDecoration을 이용해서 underline을 넣어보자.
    //Text(textDecoration = TextDecoration.Underline, text = "Hello $name!")

    // textAlign을 Center로 정렬 => 중앙정렬
    // 정렬이 되어있다는 것을 확인하기 위해 modifier설정
//    Text(
//        modifier =  Modifier.width(200.dp),
//        textAlign = TextAlign.Center,
//        text = "Hello $name!"
//    )


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TextTheme {
        Greeting("Android")
    }
}