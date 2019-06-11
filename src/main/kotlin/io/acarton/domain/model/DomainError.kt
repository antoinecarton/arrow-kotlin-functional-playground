package io.acarton.domain.model

sealed class DomainError {
    object NotFoundError : DomainError()
    object AlreadyExistError : DomainError()
    object UnknownError : DomainError()
}