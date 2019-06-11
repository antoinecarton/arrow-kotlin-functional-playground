package io.acarton.domain.model

import arrow.data.EitherT
import arrow.data.EitherTPartialOf
import arrow.data.Kleisli
import arrow.data.ReaderT
import arrow.data.extensions.eithert.monad.monad
import arrow.data.extensions.kleisli.monad.monad
import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.monad.monad
import io.acarton.domain.context.DependencyContext

private typealias Result<D, E, A> = Kleisli<EitherTPartialOf<ForIO, E>, D, A>

typealias ResultContext<A> = Result<DependencyContext, DomainError, A>

val contextMonad = ReaderT.monad<EitherTPartialOf<ForIO, DomainError>, DependencyContext>(EitherT.monad(IO.monad()))