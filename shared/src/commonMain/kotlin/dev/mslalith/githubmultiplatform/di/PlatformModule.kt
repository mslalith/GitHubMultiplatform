package dev.mslalith.githubmultiplatform.di

import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.db.SqlDriver
import dev.mslalith.githubmultiplatform.database.GitHubDatabase
import org.koin.dsl.module

expect object PlatformModule {
    fun getSqlNormalizedCacheFactory(): SqlNormalizedCacheFactory
    fun getSharedSettings(fileName: String): Settings
    fun getSqlDelightDriver(dbName: String): SqlDriver
}

internal fun getPlatformModule() = module {
    with(PlatformModule) {
        single<SqlNormalizedCacheFactory> { getSqlNormalizedCacheFactory() }
        single<Settings> { getSharedSettings(fileName = "GitHubMultiplatformSettings") }
        single<GitHubDatabase> { GitHubDatabase(driver = getSqlDelightDriver(dbName = "github_multiplatform.db")) }
    }
}
