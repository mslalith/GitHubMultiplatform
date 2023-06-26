package dev.mslalith.githubmultiplatform.di

import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.russhwolf.settings.Settings
import org.koin.dsl.module

expect object PlatformModule {
    fun getSqlNormalizedCacheFactory(): SqlNormalizedCacheFactory
    fun getSharedSettings(fileName: String): Settings
}

internal fun getPlatformModule() = module {
    with(PlatformModule) {
        single<SqlNormalizedCacheFactory> { getSqlNormalizedCacheFactory() }
        single<Settings> { getSharedSettings(fileName = "GitHubMultiplatformSettings") }
    }
}
