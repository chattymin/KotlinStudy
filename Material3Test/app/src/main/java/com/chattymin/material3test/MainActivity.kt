package com.chattymin.material3test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.chattymin.material3test.ui.theme.Material3TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3TestTheme {
                // A surface container using the 'background' color from the theme

                Column {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Red,
                        contentColor = Color.White
                    ) {
                        Greeting("Android")
                    }
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Blue,
                        contentColor = Color.Black
                    ) {
                        Greeting("Android")
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Material3TestTheme {
        Greeting("Android")
    }
}


class MainViewModel: ViewModel{

}