package dev.mslalith.githubmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
