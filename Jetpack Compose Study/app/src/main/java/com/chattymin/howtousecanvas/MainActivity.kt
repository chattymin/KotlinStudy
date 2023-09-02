package com.chattymin.howtousecanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chattymin.howtousecanvas.ui.theme.HowToUseCanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HowToUseCanvasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CanvasExample()
                }
            }
        }
    }
}

@Composable
fun CanvasExample(){
    Canvas(modifier = Modifier.size(20.dp)){
        drawLine(Color.Red, Offset(30f,10f), Offset(50f,40f))
        drawCircle(Color.Yellow, 10f, Offset(15f,40f))
        drawRect(Color.Magenta, topLeft = Offset(30f, 30f), size = Size(10f, 10f))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HowToUseCanvasTheme {
        CanvasExample()
    }
}