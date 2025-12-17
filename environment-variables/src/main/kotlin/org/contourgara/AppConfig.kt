package org.contourgara

import io.ktor.server.config.ApplicationConfig

@ConsistentCopyVisibility
data class AppConfig private constructor(
    val env: String,
) {
    companion object {
        fun from(applicationConfig: ApplicationConfig): AppConfig =
            AppConfig(
                env = applicationConfig.property("application.env").getString(),
            )
    }
}
