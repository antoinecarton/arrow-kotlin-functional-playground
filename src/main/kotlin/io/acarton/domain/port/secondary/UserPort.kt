package io.acarton.domain.port.secondary

import io.acarton.domain.model.ResultContext
import io.acarton.domain.model.User

interface UserPort {
    fun get(name: String): ResultContext<User>
}
