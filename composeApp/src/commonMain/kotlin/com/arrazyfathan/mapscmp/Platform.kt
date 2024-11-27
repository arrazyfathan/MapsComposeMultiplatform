package com.arrazyfathan.mapscmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform