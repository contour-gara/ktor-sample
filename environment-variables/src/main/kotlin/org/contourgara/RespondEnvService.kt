package org.contourgara

class RespondEnvService(
    private val appConfig: AppConfig,
) {
    fun execute(): String = appConfig.env
}
