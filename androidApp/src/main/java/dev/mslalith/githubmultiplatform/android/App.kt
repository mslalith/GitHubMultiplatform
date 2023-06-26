package dev.mslalith.githubmultiplatform.android

import android.app.Application
import dev.mslalith.githubmultiplatform.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            module {
                androidContext(this@App)
            }
        }
    }
}
