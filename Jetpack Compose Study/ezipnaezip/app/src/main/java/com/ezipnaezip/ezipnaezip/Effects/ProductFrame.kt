package com.ezipnaezip.ezipnaezip.Effects

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ezipnaezip.ezipnaezip.Button.BookmarkButton
import com.ezipnaezip.ezipnaezip.Model.Post

@Composable
fun productFrame(
    post: Post,
    navController: NavController,
    route: String,
    bookmark: Boolean,
    isMe: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(0.dp, Color.Transparent)
            .clickable {
                navController.navigate(route + "_detail_screen/${post.postNum}")
            }
            .padding(2.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        ImageFormat(url = post.thumbnailImgUrl ?: "")
        if (isMe) {
            Box(modifier = Modifier.padding(8.dp)) {
                BookmarkButton(bookmark)
            }
        }
    }
}