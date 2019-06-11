package io.acarton.infra.web

import arrow.data.fix
import arrow.data.value
import arrow.effects.fix
import io.acarton.domain.context.DependencyContext
import io.acarton.domain.model.DomainError.NotFoundError
import io.acarton.domain.model.ResultContext
import io.acarton.domain.model.User
import io.acarton.domain.model.contextMonad
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.user(dependencyContext: DependencyContext) {
    route("/user") {
        get("/{name}") {
            val name: String = call.parameters["name"]!!

            val rc: ResultContext<User> = contextMonad.binding {
                dependencyContext.userServicePort.get(name).bind()
            }.fix()

            rc.run(dependencyContext)
                .value()
                .fix()
                .unsafeRunSync()
                .fold(
                    {
                        when (it) {
                            NotFoundError -> call.respond(HttpStatusCode.NotFound)
                            else -> call.respond(HttpStatusCode.InternalServerError)
                        }
                    },
                    { call.respondText { "Hello ${it.name}" } }
                )
        }
    }
}