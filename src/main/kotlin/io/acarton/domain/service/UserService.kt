package io.acarton.domain.service

import arrow.data.Kleisli
import io.acarton.domain.model.ResultContext
import io.acarton.domain.model.User
import io.acarton.domain.port.primary.UserServicePort

class UserService : UserServicePort {
    override fun get(name: String): ResultContext<User> = Kleisli { ctx ->
        ctx.userPort.get(name).run(ctx)
    }
}