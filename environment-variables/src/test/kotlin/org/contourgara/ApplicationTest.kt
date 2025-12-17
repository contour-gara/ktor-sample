package org.contourgara

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.server.config.MapApplicationConfig
import io.ktor.server.testing.testApplication

class ApplicationTest : FunSpec({
    test("ルートエンドポイントにアクセスすると、application.env の値が取得できる") {
        testApplication {
            // setup
            application {
                module()
            }

            environment {
                config = MapApplicationConfig(
                    "application.env" to "Hello!",
                )
            }

            // execute
            val actual = client.get("/")

            // assert
            actual shouldHaveStatus 200
            actual.bodyAsText() shouldBe "Hello!"
        }
    }
})
