package com.syafii.app.routing

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun Application.installMonitoring() {
    install(CallLogging)
    install(ContentNegotiation) { json() }
}