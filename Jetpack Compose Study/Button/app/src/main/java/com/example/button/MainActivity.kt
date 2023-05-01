package com.example.button

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.button.ui.theme.ButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButtonTheme {
                ButtonExample(onButtonClicked = {
                    Toast.makeText(this, "Send clicked.", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

@Composable
fun ButtonExample(onButtonClicked: () -> Unit) {
    // 버튼을 클릭하면 Toast를 출력하도록 만들기
//    Button(onClick = onButtonClicked ) {
//        Text(text = "Send")
//    }

    // Icon을 Text 앞에 추가하기.
//    Button(onClick = onButtonClicked ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Text(text = "Send")
//    }

    // Icon과 Text사이에 Spacer넣기
//    Button(onClick = onButtonClicked ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
//        // 기본적으로 설정되는 spacer값
//        Text(text = "Send")
//    }

    // enabled를 false로 설정
//    Button(
//        onClick = onButtonClicked,
//        enabled = false
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
//        // 기본적으로 설정되는 spacer값
//        Text(text = "Send")
//    }

    // border에 BorderStroke 설정
//    Button(
//        onClick = onButtonClicked,
//        enabled = true,
//        border = BorderStroke(10.dp, Color.Black)
//        // 테두리
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
//        Text(text = "Send")
//    }

    // shape를 CircleShape로 지정
//    Button(
//        onClick = onButtonClicked,
//        enabled = true,
//        border = BorderStroke(10.dp, Color.Black),
//        shape = CircleShape
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
//        Text(text = "Send")
//    }

    // contentPadding 설정
    Button(
        onClick = onButtonClicked,
        enabled = true,
        border = BorderStroke(10.dp, Color.Black),
        shape = CircleShape,
        contentPadding = PaddingValues(20.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Send")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ButtonTheme {
        ButtonExample(onButtonClicked = {})
    }
}