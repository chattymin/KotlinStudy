package com.dongminpark.teachj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.dongminpark.teachj.ui.theme.TeachJTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeachJTheme {
                ConstraintLayoutEx()
            }
        }
    }
}

@Composable
fun ConstraintLayoutEx(){
    val constraintSet = ConstraintSet {
        val redBox = createRefFor("redBox")
        val magentaBox = createRefFor("magentaBox")
        val greenBox = createRefFor("greenBox")
        val yellowBox = createRefFor("yellowBox")

        ㅋ
        // 제약 걸기
        constrain(redBox){
            bottom.linkTo(parent.bottom, margin = 8.dp)
            end.linkTo(parent.end, margin = 4.dp)
        }

        constrain(magentaBox){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(greenBox){
            centerTo(parent)
        }

        constrain(yellowBox){
            start.linkTo(magentaBox.end)
            top.linkTo(magentaBox.bottom)
        }
    }
    ConstraintLayout(
        constraintSet,
        modifier = Modifier.fillMaxSize()
    ) {
        // val (redBox, magentaBox, greenBox, yellowBox) = createRefs()
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .layoutId("redBox")
//                .constrainAs(redBox){
////                    bottom.linkTo(parent.bottom, margin = 8.dp)
////                    end.linkTo(parent.end, margin = 4.dp)
//
//                }
        ){

        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .layoutId("magentaBox")
//                .constrainAs(magentaBox){
////                    start.linkTo(parent.start)
////                    end.linkTo(parent.end)
//                } // 양쪽에서 잡아당기니까 가운데로!!
        ){

        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .layoutId("greenBox")
//                .constrainAs(greenBox){
////                    centerTo(parent)
//                } // center~를 통해 수직, 수평, 수직수평 가운데 가느
        ){

        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .layoutId("yellowBox")
//                .constrainAs(yellowBox){
////                    start.linkTo(magentaBox.end)
////                    top.linkTo(magentaBox.bottom)
//                }
        ){

        }

    }
}

@Preview
@Composable
fun ConstraintLayoutExPreview(){
    TeachJTheme {
        ConstraintLayoutEx()
    }
}




@Composable
fun checkboxFun() {
    Row {
        FollowButton()
        MessageButton()
        HumanButton()
    }
}

@Composable
fun HumanButton() {
    var check1 by remember {
        mutableStateOf(true)
    }
    instagramMypageButtonForm(
        checked = check1,
        onCheckedChange = {
            // 추천 띄우는 기능
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "이미지에용",
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun MessageButton() {
    var check1 by remember {
        mutableStateOf(true)
    }
    instagramMypageButtonForm(
        checked = check1,
        onCheckedChange = {
            // load Message page
        }
    ) {
        ButtonTextForm(text = "MESSAGE")
    }
}

@Composable
fun FollowButton() {
    var check1 by remember {
        mutableStateOf(false)
    }
    instagramMypageButtonForm(
        checked = check1,
        onCheckedChange = {
            check1 = !check1
            // follow API Call
        }
    ) {
        ButtonTextForm(
            text = if (check1) "팔로잉" else "맞팔로우하기",
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun ButtonTextForm(
    text: String,
    fontWeight: FontWeight = FontWeight.ExtraLight
) {
    Text(
        text = text,
        fontWeight = fontWeight
    )
}


@Composable
fun instagramMypageButtonForm(
    checked: Boolean,
    onCheckedChange: () -> Unit,
    content: @Composable () -> Unit
) {
    var backgroundColor = if (checked) Color.Gray else Color.Blue
    var contentColor = Color.White

    Button(
        onClick = { onCheckedChange() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        content()
    }
}

@Preview
@Composable
fun checkboxPreview() {
    TeachJTheme {
        checkboxFun()
    }
}


@Composable
fun imgFun() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "이미지에용"
        )
        Text(text = "이미지와 함께 있는 글자")
    }
}

@Preview
@Composable
fun imgFunPreview() {
    TeachJTheme {
        imgFun()
    }
}


@Composable
fun sayHi() {
    Row() {
        Text(text = "안녕하세요 반갑습니다.")
        Text(text = "제 이름은 김정은입니다.")
    }
}

@Preview
@Composable
fun sayHiPreview() {
    TeachJTheme {
        sayHi()
    }
}