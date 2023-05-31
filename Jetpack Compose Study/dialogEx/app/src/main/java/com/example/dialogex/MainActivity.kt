package com.example.dialogex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.example.dialogex.ui.theme.DialogExTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialogExTheme {
                var showDialog by remember { mutableStateOf(false) }

                Button(onClick = { showDialog = true }) {
                    Text(text = "Dialog On")
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        confirmButton = {
                            Button(
                                onClick = {
                                    // Perform action here when dialog is confirmed
                                    showDialog = false
                                }
                            ) {
                                Text(text = "text2")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    // Perform action here when dialog is dismissed
                                    showDialog = false
                                }
                            ) {
                                Text(text = "textRow")
                            }
                        },
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        properties = DialogProperties(usePlatformDefaultWidth = false)
                    )
                }
            }
        }
    }
}