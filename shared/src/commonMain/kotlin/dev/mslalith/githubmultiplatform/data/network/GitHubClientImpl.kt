package dev.mslalith.githubmultiplatform.data.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.cache.normalized.watch
import dev.mslalith.githubmultiplatform.GetAwesomeListQuery
import dev.mslalith.githubmultiplatform.GetIssuesQuery
import dev.mslalith.githubmultiplatform.GetProfileQuery
import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import dev.mslalith.githubmultiplatform.GetStarredRepositoriesQuery
import dev.mslalith.githubmultiplatform.data.model.remote.NotificationRemote
import dev.mslalith.githubmultiplatform.data.model.remote.TrendingRepositoryRemote
import dev.mslalith.githubmultiplatform.data.settings.SharedSettings
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GitHubClientImpl : GitHubClient, KoinComponent {

    private val apolloClient by inject<ApolloClient>()
    private val httpClient by inject<HttpClient>()
    private val sharedSettings by inject<SharedSettings>()

    override fun getRepositories(): Flow<GetRepositoriesQuery.Repositories> = apolloClient
        .query(query = GetRepositoriesQuery(first = Optional.present(value = 50)))
        .watch()
        .mapNotNull { it.data?.viewer?.repositories }

    override fun getStarredRepositories(): Flow<GetStarredRepositoriesQuery.StarredRepositories> = apolloClient
        .query(query = GetStarredRepositoriesQuery(first = Optional.present(value = 50)))
        .watch()
        .mapNotNull { it.data?.viewer?.starredRepositories }

    override fun getProfileTabInfo(login: String): Flow<GetProfileQuery.Data> = apolloClient
        .query(query = GetProfileQuery(login = login))
        .watch()
        .mapNotNull { it.data }

    override fun getIssues(): Flow<GetIssuesQuery.Issues> = apolloClient
        .query(query = GetIssuesQuery(first = Optional.present(value = 50)))
        .watch()
        .mapNotNull { it.data?.viewer?.issues }

    override fun getAwesomeList(): Flow<GetAwesomeListQuery.Search> = apolloClient
        .query(query = GetAwesomeListQuery(first = Optional.present(value = 30)))
        .watch()
        .mapNotNull { it.data?.search }

    override suspend fun getTrendingRepositories(): List<TrendingRepositoryRemote> {
        return httpClient.get(urlString = "https://api.gitterapp.com/repositories").body()
    }

    override suspend fun getNotifications(): List<NotificationRemote> {
        val token = sharedSettings.accessToken
        return httpClient.get(urlString = "https://api.github.com/notifications?all=true") {
            header("Authorization", "Bearer $token")
        }.body()
    }
}
