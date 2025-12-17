package org.contourgara

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.system.OverrideMode
import io.kotest.extensions.system.withEnvironment
import io.kotest.matchers.shouldBe
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.server.testing.testApplication

class ApplicationTest : FunSpec({
    test("ルートエンドポイントにアクセスすると、'Hello!' が取得できる") {
        withEnvironment(
            environment = mapOf(
                "ENVIRONMENT_VARIABLES_ENV" to "Hello!",
            ),
            mode = OverrideMode.SetOrOverride,
        ) {
            testApplication {
                // setup
                application {
                    module()
                }

                // execute
                val actual = client.get("/")

                // assert
                actual shouldHaveStatus 200
                actual.bodyAsText() shouldBe "Hello!"
            }
        }
    }
})
