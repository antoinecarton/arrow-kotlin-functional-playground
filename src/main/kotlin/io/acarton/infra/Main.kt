package io.acarton.infra

import io.acarton.domain.context.DependencyContext
import io.acarton.domain.service.UserService
import io.acarton.infra.repository.JdbcTemplate
import io.acarton.infra.repository.UserRepository
import io.acarton.infra.web.user
import io.ktor.application.install
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val jdbcTemplate = JdbcTemplate()
    val userPort = UserRepository(jdbcTemplate)
    val dependencyContext = DependencyContext(UserService(), userPort)

    val server = embeddedServer(Netty, port = 8080) {
        install(Routing) {
            user(dependencyContext)
        }
    }
    server.start(wait = true)
}