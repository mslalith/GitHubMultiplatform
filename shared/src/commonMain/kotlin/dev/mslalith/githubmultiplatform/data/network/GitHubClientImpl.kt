package dev.mslalith.githubmultiplatform.data.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.cache.normalized.watch
import dev.mslalith.githubmultiplatform.GetIssuesQuery
import dev.mslalith.githubmultiplatform.GetProfileQuery
import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import dev.mslalith.githubmultiplatform.GetStarredRepositoriesQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GitHubClientImpl : GitHubClient, KoinComponent {

    private val apolloClient by inject<ApolloClient>()

    override suspend fun getRepositories(): Flow<GetRepositoriesQuery.Repositories> = apolloClient
        .query(query = GetRepositoriesQuery(first = Optional.present(value = 50)))
        .watch()
        .mapNotNull { it.data?.viewer?.repositories }

    override suspend fun getStarredRepositories(): Flow<GetStarredRepositoriesQuery.StarredRepositories> = apolloClient
        .query(query = GetStarredRepositoriesQuery(first = Optional.present(value = 50)))
        .watch()
        .mapNotNull { it.data?.viewer?.starredRepositories }

    override suspend fun getProfileTabInfo(login: String): Flow<GetProfileQuery.Data> = apolloClient
        .query(query = GetProfileQuery(login = login))
        .watch()
        .mapNotNull { it.data }

    override suspend fun getIssues(): Flow<GetIssuesQuery.Issues> = apolloClient
        .query(query = GetIssuesQuery(first = Optional.present(value = 50)))
        .watch()
        .mapNotNull { it.data?.viewer?.issues }
}
