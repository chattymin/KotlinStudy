package com.dongminpark.slotapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dongminpark.slotapi.ui.theme.SlotAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlotAPITheme {
                var count = remember {
                    mutableStateOf(0)
                }
                //Checkbox()
                ButtonFun(count = count.value, onClick = {count.value++}){
                    Text(text = "Click!")
                }
            }
        }
    }
}

@Composable
fun ButtonFun(
    count: Int,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { onClick() }) {
            Text(text = count.toString())
        }
        content()
    }
}


@Composable
fun ChechboxWithSlot(
    checked: Boolean,
    onCheckedChange: () -> Unit,
    content: @Composable RowScope.() -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onCheckedChange()
        }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {onCheckedChange()}
        )
        content() // 텍스트 말고 다른게 들어갈때도 재활용 가능. RowScope 기능도 적용 가능.
    }
}

@Composable
fun Checkbox(){
    var check by remember {
        mutableStateOf(false)
    }

    ChechboxWithSlot(checked = check, onCheckedChange = { check = !check }) {
        Text("hi")
    }
}


@Preview
@Composable
fun CheckboxWithSlotPreview(){
    SlotAPITheme {
        Checkbox()
    }
}