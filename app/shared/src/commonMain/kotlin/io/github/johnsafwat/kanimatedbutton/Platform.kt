package io.github.johnsafwat.kanimatedbutton

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform