package dev.mslalith.githubmultiplatform.usecase.base

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

abstract class FlowUseCase<I, O> : KoinComponent {
    abstract suspend fun run(input: I): Flow<O>

    abstract class NoParams<O> : FlowUseCase<Unit, O>() {
        abstract suspend fun run(): Flow<O>
        override suspend fun run(input: Unit): Flow<O> = run()
    }
}
