plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.syafii.core"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("org.mindrot:jbcrypt:0.4")
    implementation(libs.ktor.server.auth.jwt)
}

kotlin {
    jvmToolchain(17)
}
