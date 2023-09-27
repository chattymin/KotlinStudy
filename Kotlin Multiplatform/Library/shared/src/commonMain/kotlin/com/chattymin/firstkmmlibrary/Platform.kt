package com.chattymin.firstkmmlibrary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform