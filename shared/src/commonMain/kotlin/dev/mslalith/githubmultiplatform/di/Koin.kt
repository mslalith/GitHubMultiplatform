package dev.mslalith.githubmultiplatform.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(
    moduleProvider: KoinApplication.() -> Module = { module {  } }
) {
    startKoin {
        modules(
            moduleProvider(),
            commonModule,
            networkModule,
            voyagerModule,
            getPlatformModule()
        )
    }
}
