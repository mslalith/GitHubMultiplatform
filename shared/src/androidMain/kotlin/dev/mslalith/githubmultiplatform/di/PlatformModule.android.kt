package dev.mslalith.githubmultiplatform.di

import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import org.koin.dsl.module

actual object PlatformModule {
    actual fun getSqlNormalizedCacheFactory() = SqlNormalizedCacheFactory("apollo.db")
}