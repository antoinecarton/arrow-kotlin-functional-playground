package io.acarton.infra.repository

import arrow.core.right
import arrow.data.EitherT
import arrow.data.Kleisli
import arrow.effects.IO
import io.acarton.domain.model.ResultContext
import io.acarton.domain.model.User
import io.acarton.domain.port.secondary.UserPort

class JdbcTemplate { // This could be a class from a lib
    fun simulateQueryUser(name: String) = User(name)
}

class UserRepository(private val jdbcTemplate: JdbcTemplate) : UserPort {
    override fun get(name: String): ResultContext<User> = Kleisli { ctx ->
        EitherT(
            IO {
                jdbcTemplate.simulateQueryUser(name).right() // Whatever IO call
            }
        )
    }
}