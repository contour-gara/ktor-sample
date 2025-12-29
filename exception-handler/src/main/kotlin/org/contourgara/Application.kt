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

    routing {
        get("/") {
            call.respondText { "Hello!" }
        }
    }

    createExceptionRoute()
    createGlobalExceptionHandler()
}

fun Application.createExceptionRoute() {
    routing {
        get("/my-exception") {
            throw MyException()
        }

        get("/exception") {
            throw RuntimeException("throw by /exception")
        }
    }
}

class MyException() : RuntimeException("throw by /my-exception")

fun Application.createGlobalExceptionHandler() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is MyException -> {
                    call.application.environment.log.warn(cause.message?: "Exception of type ${cause::class}", cause)
                    call.respondText(status = HttpStatusCode.BadRequest, text = cause.message ?: "")
                }
                else -> {
                    call.application.environment.log.error(cause)
                    call.respondText(status = HttpStatusCode.InternalServerError, text = cause.message ?: "")
                }
            }
        }
    }
}
