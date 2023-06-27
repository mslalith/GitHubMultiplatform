package dev.mslalith.githubmultiplatform.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(
    moduleProvider: (KoinApplication.() -> Module)? = null
) {
    startKoin {
        moduleProvider?.let { modules(it()) }
        modules(
            commonModule,
            networkModule,
            voyagerModule,
            getPlatformModule()
        )
    }
}
