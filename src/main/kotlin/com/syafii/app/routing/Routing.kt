package com.syafii.app.routing

import com.syafii.core.domain.model.Greeting
import com.syafii.core.domain.usecase.GetGreetingUseCase
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin

fun Routing.greetingRoutes() {
    // Ambil UseCase dari Koin secara manual
    val getGreetingUseCase: GetGreetingUseCase = application.getKoin().get()
    get("/") {
        val greeting: Greeting = getGreetingUseCase.execute()
        call.respond(greeting)
    }
}