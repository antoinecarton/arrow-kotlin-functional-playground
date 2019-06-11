package io.acarton.domain.context

import io.acarton.domain.port.primary.UserServicePort
import io.acarton.domain.port.secondary.UserPort

data class DependencyContext(
    val userServicePort: UserServicePort,
    val userPort: UserPort
)