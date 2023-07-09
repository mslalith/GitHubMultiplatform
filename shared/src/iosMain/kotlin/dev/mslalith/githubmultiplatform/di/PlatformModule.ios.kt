package dev.mslalith.githubmultiplatform.di

import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import dev.mslalith.githubmultiplatform.database.GitHubDatabase

actual object PlatformModule {
    actual fun getSqlNormalizedCacheFactory() = SqlNormalizedCacheFactory("apollo.db")

    @OptIn(ExperimentalSettingsImplementation::class)
    actual fun getSharedSettings(fileName: String): Settings = KeychainSettings(service = fileName)

    actual fun getSqlDelightDriver(dbName: String): SqlDriver = NativeSqliteDriver(
        schema = GitHubDatabase.Schema,
        name = dbName
    )
}
