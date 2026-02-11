package org.contourgara

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.http.content.singlePageApplication
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    install(plugin = CallLogging) {
        level = Level.DEBUG
    }

    routing {
        get(path = "/health") {
            call.respondText { "Hello!" }
        }

        // コメントアウトしている場合、/about にリクエストすると SPA の about が返る
        // コメントアウトしている場合、/test のような定義していない path にリクエストすると、SPA が返るが、"No routes matched location "/test"" とブラウザのコンソールに出る
        // コメントアウトしていない場合、/about にリクエストすると Ktor の about が返る
        // コメントアウトしていない場合、/ にリクエストすると SPA の about が返る
        get(path = "/about") {
            call.respondText { "about" }
        }

        singlePageApplication {
            useResources = true
            filesPath = "frontend"
            defaultPage = "index.html"
        }
    }
}
