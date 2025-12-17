package org.contourgara

class RespondEnvService {
    fun execute(): String = System.getenv("ENVIRONMENT_VARIABLES_ENV")
}
