package dev.mslalith.githubmultiplatform.data.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.cache.normalized.watch
import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GitHubClientImpl : GitHubClient, KoinComponent {

    private val apolloClient by inject<ApolloClient>()

    override suspend fun getRepositories(): Flow<GetRepositoriesQuery.Repositories> = apolloClient
        .query(query = GetRepositoriesQuery(first = Optional.present(value = 10)))
        .watch()
        .mapNotNull { it.data?.viewer?.repositories }
}
