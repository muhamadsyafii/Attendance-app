package com.syafii.app

//import com.syafii.app.di.configureFrameworks
//import com.syafii.app.routing.configureRouting
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import io.ktor.server.netty.EngineMain
import com.syafii.app.di.koinModule
import com.syafii.app.routing.greetingRoutes
import io.ktor.server.plugins.calllogging.CallLogging
import org.koin.ktor.plugin.Koin
import org.slf4j.event.Level

//fun main(args: Array<String>) {
//    EngineMain.main(args)
//}

/*
fun Application.module() {
    configureSerialization()
    configureSecurity()
    configureMonitoring()
    configureDatabases()
    configureFrameworks()
    configureRouting()
}
*/

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(ContentNegotiation) { json() }
    install(Koin) { modules(koinModule) }
    install(CallLogging){
        level  = Level.DEBUG
    }
    routing {
        greetingRoutes()
    }
}