package org.contourgara

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.system.OverrideMode
import io.kotest.extensions.system.withEnvironment
import io.kotest.matchers.shouldBe

class RespondEnvServiceTest : FunSpec({
    test("環境変数 ENVIRONMENT_VARIABLES_ENV の値を取得し返す") {
        withEnvironment(
            environment = mapOf(
                "ENVIRONMENT_VARIABLES_ENV" to "Hello!",
            ),
            mode = OverrideMode.SetOrOverride,
        ) {
            // setup
            val sut = RespondEnvService()

            // execute
            val actual = sut.execute()

            // assert
            val expected = "Hello!"
            actual shouldBe expected
        }
    }
})
