plugins {
    kotlin("jvm")

}

group = "com.syafii.data"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    testImplementation(kotlin("test"))
//    implementation(libs.exposed.core)
//    implementation(libs.exposed.jdbc)

    implementation("org.jetbrains.exposed:exposed-core:1.0.0-beta-4")
    implementation("org.jetbrains.exposed:exposed-jdbc:1.0.0-beta-4")
    implementation("org.jetbrains.exposed:exposed-dao:1.0.0-beta-4")
    implementation("org.jetbrains.exposed:exposed-java-time:1.0.0-beta-4")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:1.0.0-beta-4")
    implementation("org.jetbrains.exposed:exposed-json:1.0.0-beta-4")

    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.mindrot:jbcrypt:0.4")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}