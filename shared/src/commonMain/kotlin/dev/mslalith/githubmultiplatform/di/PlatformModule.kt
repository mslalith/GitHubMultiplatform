package dev.mslalith.githubmultiplatform.di

import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import org.koin.dsl.module

expect object PlatformModule {
    fun getSqlNormalizedCacheFactory(): SqlNormalizedCacheFactory
}

internal fun getPlatformModule() = module {
    single { PlatformModule.getSqlNormalizedCacheFactory() }
}
