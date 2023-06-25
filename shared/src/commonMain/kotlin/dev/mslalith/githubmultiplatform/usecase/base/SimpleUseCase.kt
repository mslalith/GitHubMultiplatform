package dev.mslalith.githubmultiplatform.usecase.base

import org.koin.core.component.KoinComponent

abstract class SimpleUseCase<I, O> : KoinComponent {
    abstract fun run(input: I): O

    abstract class NoParams<O> : SimpleUseCase<Unit, O>() {
        abstract fun run(): O
        override fun run(input: Unit): O = run()
    }
}
