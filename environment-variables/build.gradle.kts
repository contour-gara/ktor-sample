plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.logback.classic)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.extensions.ktor)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.mockk)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("--add-opens=java.base/java.util=ALL-UNNAMED")
}
