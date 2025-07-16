
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.ktor)
    application
}

group = "com.syafii.app"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    repositories { mavenCentral() }
}

ktor {
    development = true
}


dependencies {
    implementation("org.mindrot:jbcrypt:0.4")
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.postgresql)
    implementation(libs.h2)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
    implementation("org.jetbrains.exposed:exposed-core:1.0.0-beta-4")
    implementation("org.jetbrains.exposed:exposed-jdbc:1.0.0-beta-4")
    implementation("org.jetbrains.exposed:exposed-dao:1.0.0-beta-4")
    implementation("org.jetbrains.exposed:exposed-java-time:1.0.0-beta-4")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:1.0.0-beta-4")
    implementation("org.jetbrains.exposed:exposed-json:1.0.0-beta-4")
    implementation("io.ktor:ktor-server-status-pages-jvm:3.2.1")

}
