package org.contourgara

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    val appConfig = AppConfig.from(applicationConfig = environment.config)

    routing {
        get("/") {
            call.respondText { RespondEnvService(appConfig = appConfig).execute() }
        }
    }
}
