package dev.mslalith.githubmultiplatform.di

import dev.mslalith.githubmultiplatform.ui.login.LoginScreenModel
import org.koin.dsl.module

internal val voyagerModule = module {
    factory {
        LoginScreenModel()
    }
}
