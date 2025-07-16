package com.syafii.app

import com.syafii.app.api.authRoutes
import com.syafii.app.di.koinModule
import com.syafii.app.routing.greetingRoutes
import com.syafii.app.routing.installErrorHandling
import com.syafii.app.routing.installMonitoring
import com.syafii.app.routing.installSecurity
import com.syafii.app.routing.installTiming
import com.syafii.core.domain.model.JwtConfig
import com.syafii.data.db.DatabaseFactory
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
//    install(ContentNegotiation) { json() }

    val dbConfig = environment.config.config("database")
    val url    = dbConfig.property("url").getString()
    val driver = dbConfig.property("driver").getString()
    val user   = dbConfig.property("user").getString()
    val pwd    = dbConfig.property("password").getString()

    // 2) Initialize Exposed/Hikari – must be done before any transaction { ... }
    DatabaseFactory.init(
        url    = url,
        driver = driver,
        user   = user,
        pwd    = pwd
    )

    val jwtSecret   = environment.config.property("jwt.secret").getString()
    val jwtIssuer   = environment.config.property("jwt.issuer").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()

    val jwtConfig = JwtConfig(
        secret   = jwtSecret,
        issuer   = jwtIssuer,
        audience = jwtAudience
    )

    install(Koin) {
        modules(
            module {
                single { jwtConfig }
            },
            koinModule
        )
    }
    installMonitoring()
    installSecurity(jwtConfig.verifier)
    installTiming()
    installErrorHandling()

    routing {
        greetingRoutes()
        authRoutes()
    }
}
