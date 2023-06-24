package dev.mslalith.githubmultiplatform.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(commonModule)
    }
}
