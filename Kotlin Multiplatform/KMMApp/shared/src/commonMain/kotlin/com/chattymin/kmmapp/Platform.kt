package com.chattymin.kmmapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform