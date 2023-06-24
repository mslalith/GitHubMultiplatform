package dev.mslalith.githubmultiplatform.android

import android.app.Application
import dev.mslalith.githubmultiplatform.di.initKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
