package com.ezipnaezip.ezipnaezip.Button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ezipnaezip.ezipnaezip.R


@Composable
fun BookmarkButton(bookmark: Boolean){
    var isBookmark by rememberSaveable {mutableStateOf(bookmark)}

    Icon(
        painter = if (isBookmark) painterResource(id = R.drawable.bookmark_color) else painterResource(id = R.drawable.bookmark_outline),
        contentDescription = "Bookmark",
        modifier = Modifier
            .size(26.dp)
            .clickable {
                isBookmark = !isBookmark
                // 북마크 추가 Api 호출
                // 클릭하면 imageVector 변경
            },
        tint = Color.Unspecified
    )
}