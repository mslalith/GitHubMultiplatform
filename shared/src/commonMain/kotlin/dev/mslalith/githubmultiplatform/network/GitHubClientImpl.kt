package dev.mslalith.githubmultiplatform.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

internal class GitHubClientImpl(
    private val apolloClient: ApolloClient
) : GitHubClient {

    override suspend fun getRepositories(): Flow<GetRepositoriesQuery.Repositories> = apolloClient
        .query(query = GetRepositoriesQuery(first = Optional.present(value = 10)))
        .toFlow()
        .mapNotNull { it.data?.viewer?.repositories }
}