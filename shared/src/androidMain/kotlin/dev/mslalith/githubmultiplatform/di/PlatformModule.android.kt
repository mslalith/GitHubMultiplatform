package dev.mslalith.githubmultiplatform.di

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dev.mslalith.githubmultiplatform.database.GitHubDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual object PlatformModule : KoinComponent {

    private val context by inject<Context>()

    actual fun getSqlNormalizedCacheFactory() = SqlNormalizedCacheFactory("apollo.db")

    actual fun getSharedSettings(fileName: String): Settings = SharedPreferencesSettings(
        commit = false,
        delegate = EncryptedSharedPreferences.create(
            fileName,
            "GitHubMultiplatform",
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    )

    actual fun getSqlDelightDriver(dbName: String): SqlDriver = AndroidSqliteDriver(
        schema = GitHubDatabase.Schema,
        context = context,
        name = dbName
    )
}
