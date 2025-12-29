package org.contourgara

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.util.logging.error
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    install(CallLogging) {
        level = Level.DEBUG
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            if (cause is RuntimeException) {
                call.application.environment.log.error(cause)
                call.respondText(status = HttpStatusCode.BadRequest, text = cause.message ?: "")
            } else {
                call.application.environment.log.error(cause)
                call.respondText(status = HttpStatusCode.InternalServerError, text = cause.message ?: "")
            }
        }
    }

    routing {
        get("/") {
            call.respondText { "Hello!" }
        }

        get("/exception") {
            throw RuntimeException("throw by /exception")
        }
    }
}
