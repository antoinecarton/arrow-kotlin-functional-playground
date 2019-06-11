package io.acarton.domain.port.primary

import io.acarton.domain.model.ResultContext
import io.acarton.domain.model.User

interface UserServicePort {
    fun get(name: String): ResultContext<User>
}