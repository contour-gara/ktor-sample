package org.contourgara

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class RespondEnvServiceTest : FunSpec({
    test("環境変数 ENVIRONMENT_VARIABLES_ENV の値を取得し返す") {
        // setup
        val appConfig = mockk<AppConfig>()
        every { appConfig.env } returns "Hello!"
        val sut = RespondEnvService(appConfig)

        // execute
        val actual = sut.execute()

        // assert
        val expected = "Hello!"
        actual shouldBe expected
    }
})
